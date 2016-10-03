package com.example.win81user.myproject.API;

import com.example.win81user.myproject.Model.ItemModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Win81 User on 28/9/2559.
 */
public interface FeedApi {
    @GET("feed.json")
    Call<ItemModel> getShout();
}
