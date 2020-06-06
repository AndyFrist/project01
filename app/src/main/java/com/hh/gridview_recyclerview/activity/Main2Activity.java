package com.hh.gridview_recyclerview.activity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.materialdesign.MaterialDesignsActivity;
import com.hh.gridview_recyclerview.pulltorefresh.MainActivity;
import com.hh.gridview_recyclerview.utils.LogUtil;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Activitylife";
    private Button btn_left_1, btn_left_2,   btn_left_5, btn_left_6, btn_left_7,  myprocess, brokenline, sockeybtn, expandableListView;
    private Button myimageview, water_id, numberpick, swipemenu, indexbar, webview_id, over_scroll_by, input_soft, pull, animation, reflash, android78, clock;
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

        btn_left_5 = (Button) findViewById(R.id.btn_left_5);
        btn_left_5.setOnClickListener(this);
        btn_left_6 = (Button) findViewById(R.id.btn_left_6);
        btn_left_6.setOnClickListener(this);
        btn_left_7 = (Button) findViewById(R.id.btn_left_7);
        btn_left_7.setOnClickListener(this);
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
        sockeybtn = (Button) findViewById(R.id.sockeybtn);
        sockeybtn.setOnClickListener(this);
        expandableListView = (Button) findViewById(R.id.expandableListView);
        expandableListView.setOnClickListener(this);
        reflash = (Button) findViewById(R.id.reflash);
        reflash.setOnClickListener(this);
        android78 = (Button) findViewById(R.id.android78);
        android78.setOnClickListener(this);

        clock = (Button) findViewById(R.id.clock);
        clock.setOnClickListener(this);

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
                     case R.id.btn_left_5:
                intent.setClass(this, TabLauyoutActivity.class);
                break;
            case R.id.btn_left_6:
                intent.setClass(this, MyrecycleviewActivity.class);
                break;
            case R.id.btn_left_7:
                intent.setClass(this, MaterialDesignsActivity.class);
                break;
            case R.id.myprocess:
                intent.setClass(this, RingProcessActivity.class);
                break;
            case R.id.brokenline:
                intent.setClass(this, BrokenlineActivity.class);
                break;
            case R.id.myimageview:
                intent.setClass(this, MyImageViewActivity.class);
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
            case R.id.sockeybtn:
                intent.setClass(this, SocketActivity.class);
                break;
            case R.id.expandableListView:
                intent.setClass(this, ExpandableListViewActivity.class);
                break;
            case R.id.reflash:
                intent.setClass(this, MainActivity.class);
                break;
            case R.id.android78:
                intent.setClass(this, Android78Activity.class);
                break;
            case R.id.clock:
                intent.setClass(this, ClockActivity.class);
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
