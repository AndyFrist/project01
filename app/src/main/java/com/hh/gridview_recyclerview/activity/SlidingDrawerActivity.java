package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SlidingDrawer;

import com.hh.gridview_recyclerview.R;

public class SlidingDrawerActivity extends AppCompatActivity {

    private SlidingDrawer sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_drawer);
        sd = (SlidingDrawer) findViewById(R.id.sd);


        sd.lock();
    }

    public void open(View view) {
        sd.open();
    }

    public void close(View view) {
        sd.close();
    }
}
