package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.RadarView;


public class waterActivity extends AppCompatActivity {
    private RadarView radarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        radarView=(RadarView) findViewById(R.id.asdf);
        //开始动画
        radarView.startRippleAnimation();
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //停止动画
                radarView.stopRippleAnimation();
            }
        });
    }
}
