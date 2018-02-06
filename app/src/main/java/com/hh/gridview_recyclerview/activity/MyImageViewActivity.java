package com.hh.gridview_recyclerview.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.RoundImageView;

public class MyImageViewActivity extends AppCompatActivity {
    private RoundImageView rouond_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_view);
        rouond_iv = (RoundImageView) findViewById(R.id.rouond_iv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rouond_iv.setRectAdius(40);
            }
        },3003);


    }
}
