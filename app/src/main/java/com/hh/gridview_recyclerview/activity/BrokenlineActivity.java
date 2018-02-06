package com.hh.gridview_recyclerview.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.BrokenLineView;
import com.hh.gridview_recyclerview.utils.LogUtil;

import java.util.ArrayList;

public class BrokenlineActivity extends AppCompatActivity {
    private static final String TAG = "BrokenlineActivity";
    private BrokenLineView broken_line;
    private ArrayList<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brokenline);
        broken_line = (BrokenLineView) findViewById(R.id.broken_line);

        for (int i = 0; i < 7; i++) {
            list.add((int) (Math.random() * 1000));
            LogUtil.d(TAG, "随机" + i);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                broken_line.setData(list);
            }
        },1000);

    }
}
