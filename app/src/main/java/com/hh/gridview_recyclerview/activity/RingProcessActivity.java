package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.RingProgress;

public class RingProcessActivity extends AppCompatActivity implements View.OnClickListener {

    private RingProgress my_progress;
    private Button flash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_process);
        my_progress = (RingProgress) findViewById(R.id.my_progress);
        flash = (Button) findViewById(R.id.flash);
        flash.setOnClickListener(this);
    }

    int value = 500;

    @Override
    public void onClick(View view) {
        my_progress.set(value = value + 1000);
    }
}
