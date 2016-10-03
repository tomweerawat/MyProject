package com.example.win81user.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.win81user.myproject.View.ShowFeed;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
     TextView tfeed;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);

        prepareview();

    }
    private void prepareview(){

        tfeed = (TextView)findViewById(R.id.btn_feed);
        tfeed.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_feed:
                //progress.setVisibility(View.VISIBLE);
                Intent i = new Intent(LoginActivity.this,ShowFeed.class);
                startActivity(i);

        }

    }
}
