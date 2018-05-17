package com.hh.gridview_recyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.materialdesign.MaterialDesignActivity;
import com.hh.gridview_recyclerview.service.ForgoundService;
import com.hh.gridview_recyclerview.utils.LogUtil;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Activitylife";
    private Button btn_left_1, btn_left_2, btn_left_3, btn_left_4, btn_left_5, btn_left_6, btn_left_7, okhttp, myprocess, brokenline;
    private Button myimageview, water_id, numberpick, swipemenu, indexbar, webview_id, over_scroll_by, input_soft, pull, animation;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        LogUtil.d(TAG, "onCreate");
    }

    private void initView() {
        intent = new Intent();
        btn_left_1 = (Button) findViewById(R.id.btn_left_1);
        btn_left_1.setOnClickListener(this);
        btn_left_2 = (Button) findViewById(R.id.btn_left_2);
        btn_left_2.setOnClickListener(this);
        btn_left_3 = (Button) findViewById(R.id.btn_left_3);
        btn_left_3.setOnClickListener(this);
        btn_left_4 = (Button) findViewById(R.id.btn_left_4);
        btn_left_4.setOnClickListener(this);
        btn_left_5 = (Button) findViewById(R.id.btn_left_5);
        btn_left_5.setOnClickListener(this);
        btn_left_6 = (Button) findViewById(R.id.btn_left_6);
        btn_left_6.setOnClickListener(this);
        btn_left_7 = (Button) findViewById(R.id.btn_left_7);
        btn_left_7.setOnClickListener(this);
        okhttp = (Button) findViewById(R.id.okhttp);
        okhttp.setOnClickListener(this);
        myprocess = (Button) findViewById(R.id.myprocess);
        myprocess.setOnClickListener(this);
        brokenline = (Button) findViewById(R.id.brokenline);
        brokenline.setOnClickListener(this);
        myimageview = (Button) findViewById(R.id.myimageview);
        myimageview.setOnClickListener(this);
        water_id = (Button) findViewById(R.id.water_id);
        water_id.setOnClickListener(this);
        numberpick = (Button) findViewById(R.id.numberpick);
        numberpick.setOnClickListener(this);
        swipemenu = (Button) findViewById(R.id.swipemenu);
        swipemenu.setOnClickListener(this);
        indexbar = (Button) findViewById(R.id.indexbar);
        indexbar.setOnClickListener(this);
        webview_id = (Button) findViewById(R.id.webview_id);
        webview_id.setOnClickListener(this);
        over_scroll_by = (Button) findViewById(R.id.over_scroll_by);
        over_scroll_by.setOnClickListener(this);
        input_soft = (Button) findViewById(R.id.input_soft);
        input_soft.setOnClickListener(this);
        pull = (Button) findViewById(R.id.pull);
        pull.setOnClickListener(this);
        animation = (Button) findViewById(R.id.animation);
        animation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left_1:
                intent.setClass(this, GridViewActivity.class);
                break;
            case R.id.btn_left_2:
                intent.setClass(this, RecyclerActivity.class);
                break;
            case R.id.btn_left_3:
                intent.setClass(this, LocationActivity.class);
                break;
            case R.id.btn_left_4:
                intent.setClass(this, HnadlerThreadActivity.class);
                break;
            case R.id.btn_left_5:
                intent.setClass(this, TabLauyoutActivity.class);
                break;
            case R.id.btn_left_6:
                intent.setClass(this, MyrecycleviewActivity.class);
                break;
            case R.id.btn_left_7:
                intent.setClass(this, MaterialDesignActivity.class);
                break;
            case R.id.okhttp:
                intent.setClass(this, OkhttpActivity.class);
                break;
            case R.id.myprocess:
                intent.setClass(this, RingProcessActivity.class);
                break;
            case R.id.brokenline:
                intent.setClass(this, BrokenlineActivity.class);
                break;
            case R.id.myimageview:
                intent.setClass(this, ForgoundService.class);

            break;
            case R.id.water_id:
                intent.setClass(this, waterActivity.class);
                break;
            case R.id.numberpick:
                intent.setClass(this, NumberPickActivity.class);
                break;
            case R.id.swipemenu:
                intent.setClass(this, SlideActivity.class);
                break;
            case R.id.indexbar:
                intent.setClass(this, IndexActivity.class);
                break;
            case R.id.webview_id:
                intent.setClass(this, WebViewActivity.class);
                break;
            case R.id.over_scroll_by:
                intent.setClass(this, OverScrollyByActivity.class);
                break;
            case R.id.input_soft:
                intent.setClass(this, InputActivity.class);
                break;
            case R.id.pull:
                intent.setClass(this, PullActivity.class);
                break;
            case R.id.animation:
                intent.setClass(this, AnimatorActivity.class);
                break;
            default:

        }
//        startService(intent);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LogUtil.d(TAG, "onPostResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtil.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
    }
}
