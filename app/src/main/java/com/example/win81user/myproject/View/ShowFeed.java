package com.example.win81user.myproject.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.win81user.myproject.API.FeedApi;
import com.example.win81user.myproject.Adapter.DataAdapter;
import com.example.win81user.myproject.Model.ItemModel;
import com.example.win81user.myproject.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Win81 User on 28/9/2559.
 */

public class ShowFeed extends Activity implements Callback<ItemModel> {
    SwipeRefreshLayout swipeRefreshLayout;
    Retrofit retrofit;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    DataAdapter dataAdapter;
    //http://192.168.25.2:8181/weerawat/
    String API = "http://api.androidhive.info/feed/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_feed);
        intitView();
    }
    private void intitView(){
      /*  RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        DataAdapter dataAdapter = new DataAdapter();
        recyclerView.setAdapter(dataAdapter);*/
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Log.e("OkHttpClient","connected"+client);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Log.e("retrofit2","connected"+API);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(dataAdapter);

        apiCall(retrofit);
         Log.e("apicall","connected"+retrofit);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                apiCall(retrofit);
                Log.e("onRefresh","Success Refresh");
            }
        });
    }


    private void apiCall(Retrofit retrofit) {
        FeedApi myApi = retrofit.create(FeedApi.class);
        Call<ItemModel> call = myApi.getShout();
        call.enqueue(this);
        Log.e("apiCall","Success Call");
    }


    @Override
    public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
        ItemModel itemModel = response.body();
        DataAdapter adapter = new DataAdapter(itemModel);

        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
        Log.e("OnResponse","Response");
    }

    @Override
    public void onFailure(Call<ItemModel> call, Throwable t) {
        Toast.makeText(getApplicationContext(),"Failed !",Toast.LENGTH_LONG).show();
    }

}
