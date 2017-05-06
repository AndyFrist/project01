package com.hh.gridview_recyclerview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.recyclerView.HomeActivity;
import com.hh.gridview_recyclerview.utils.IntentUtils;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button gridview_id;
    private Button recycler_id;
    private Button coordinatorLayout;
    private Button hot_repair;
    private Button sliding;
    private Button drawlayout;
    private Button slidemenu;
    private Button bigBitmap;
    private Button touchListen;
    private Button retrofit;
    private Button rx_java;

    private Button fragment, surfaceview, drawView;


    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        intent = new Intent();
        recycler_id = (Button) findViewById(R.id.recycler_id);
        gridview_id = (Button) findViewById(R.id.gridview_id);
        coordinatorLayout = (Button) findViewById(R.id.coordinatorLayout);
        hot_repair = (Button) findViewById(R.id.hot_repair);
        sliding = (Button) findViewById(R.id.sliding);
        drawlayout = (Button) findViewById(R.id.drawlayout);
        slidemenu = (Button) findViewById(R.id.slidemenu);
        bigBitmap = (Button) findViewById(R.id.bigBitmap);
        touchListen = (Button) findViewById(R.id.touchListen);
        retrofit = (Button) findViewById(R.id.retrofit);
        rx_java = (Button) findViewById(R.id.rx_java);
        fragment = (Button) findViewById(R.id.fragment);
        surfaceview = (Button) findViewById(R.id.surfaceview);
        drawView = (Button) findViewById(R.id.drawView);

        gridview_id.setOnClickListener(this);
        recycler_id.setOnClickListener(this);
        coordinatorLayout.setOnClickListener(this);
        hot_repair.setOnClickListener(this);
        sliding.setOnClickListener(this);
        drawlayout.setOnClickListener(this);
        slidemenu.setOnClickListener(this);
        bigBitmap.setOnClickListener(this);
        touchListen.setOnClickListener(this);
        retrofit.setOnClickListener(this);
        rx_java.setOnClickListener(this);
        fragment.setOnClickListener(this);
        surfaceview.setOnClickListener(this);
        drawView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gridview_id:
                intent.setClass(this, GridViewActivity.class);
                startActivity(intent);
                break;

            case R.id.recycler_id:
//                intent.setClass(this, RecyclerActivity.class);
                intent.setClass(this, HomeActivity.class);

                startActivity(intent);
                break;

            case R.id.coordinatorLayout:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.hot_repair:
                intent.setClass(this, HotRepairActivity.class);
                startActivity(intent);
                break;

            case R.id.sliding:
                intent.setClass(this, SlidingDrawerActivity.class);
                startActivity(intent);
                break;

            case R.id.drawlayout:
                intent.setClass(this, DrawlayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.slidemenu:
                intent.setClass(this, SlideMenuActivity.class);
                startActivity(intent);
                break;

            case R.id.bigBitmap:
                intent.setClass(this, BigBitMapActivity.class);
                startActivity(intent);
                break;
            case R.id.touchListen:
                intent.setClass(this, TouchListenActivity.class);
                startActivity(intent);
                break;
            case R.id.retrofit:
                intent.setClass(this, RetrofitActivity.class);
                startActivity(intent);
                break;
            case R.id.rx_java:
                intent.setClass(this, RxJavaActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment:
                intent.setClass(this, FragmentActivity.class);
                IntentUtils.getInstance().startActivity(this, intent);
                break;
            case R.id.surfaceview:
                intent.setClass(this, SurfaceViewActivity.class);
                IntentUtils.getInstance().startActivity(this, intent);
                break;
            case R.id.drawView:
                intent.setClass(this, DragViewActivity.class);
                IntentUtils.getInstance().startActivity(this, intent);
                break;
        }
    }
}