package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.hh.gridview_recyclerview.R;

public class DrawlayoutActivity extends AppCompatActivity {

    DrawerLayout drawlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawlayout);
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayouttt);
        drawlayout.openDrawer(Gravity.LEFT);    //默认打开左边面板
    }
}
