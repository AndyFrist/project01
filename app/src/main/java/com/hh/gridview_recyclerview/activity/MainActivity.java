package com.hh.gridview_recyclerview.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.myinteraface.PermissionListener;
import com.hh.gridview_recyclerview.recyclerView.HomeActivity;
import com.hh.gridview_recyclerview.utils.IntentUtils;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button gridview_id;
    private Button recycler_id;
    private Button coordinatorLayout;
    private Button sliding;
    private Button drawlayout;
    private Button slidemenu;
    private Button touchListen;
    private Button webview_music;

    private Button  map_btn, surfaceview, drawView, myseekBar, touchlove, camera2btn, camerabtn, tth, six_principle,longimage;


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
        sliding = (Button) findViewById(R.id.sliding);
        drawlayout = (Button) findViewById(R.id.drawlayout);
        slidemenu = (Button) findViewById(R.id.slidemenu);
        touchListen = (Button) findViewById(R.id.touchListen);
        surfaceview = (Button) findViewById(R.id.surfaceview);
        drawView = (Button) findViewById(R.id.drawView);
        myseekBar = (Button) findViewById(R.id.myseekBar);
        touchlove = (Button) findViewById(R.id.touchlove);

        camera2btn = (Button) findViewById(R.id.camera2btn);
        camerabtn = (Button) findViewById(R.id.camerabtn);
        tth = (Button) findViewById(R.id.tth);
        six_principle = (Button) findViewById(R.id.six_principle);
        map_btn = (Button) findViewById(R.id.map_btn);
        webview_music = (Button) findViewById(R.id.webview_music);
        longimage = (Button) findViewById(R.id.longimage);


        map_btn.setOnClickListener(this);
        gridview_id.setOnClickListener(this);
        recycler_id.setOnClickListener(this);
        coordinatorLayout.setOnClickListener(this);
        sliding.setOnClickListener(this);
        drawlayout.setOnClickListener(this);
        slidemenu.setOnClickListener(this);
        touchListen.setOnClickListener(this);
        surfaceview.setOnClickListener(this);
        drawView.setOnClickListener(this);
        myseekBar.setOnClickListener(this);
        touchlove.setOnClickListener(this);
               camera2btn.setOnClickListener(this);
        camerabtn.setOnClickListener(this);
        tth.setOnClickListener(this);
        six_principle.setOnClickListener(this);
        webview_music.setOnClickListener(this);
        longimage.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gridview_id:
                intent.setClass(this, Main2Activity.class);
                startActivity(intent);
                break;

            case R.id.recycler_id:
                intent.setClass(this, HomeActivity.class);
                startActivity(intent);
                break;

            case R.id.coordinatorLayout:
                intent.setClass(this, CoordinatorLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.map_btn:
                intent.setClass(this, MapActivity.class);
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

            case R.id.touchListen:
                intent.setClass(this, TouchListenActivity.class);
                startActivity(intent);
                break;

            case R.id.surfaceview:
                intent.setClass(this, SurfaceViewActivity.class);
                IntentUtils.getInstance().startActivity(this, intent);
                break;
            case R.id.drawView:
                intent.setClass(this, DragViewActivity.class);
                IntentUtils.getInstance().startActivity(this, intent);
                break;
            case R.id.myseekBar:
                intent.setClass(this, MyseekbarActivity.class);
                startActivity(intent);
                break;
            case R.id.touchlove:
                intent.setClass(this, TestPraiseActivity.class);
                startActivity(intent);
                break;

            case R.id.camera2btn:
                BaseActivity.requestPermissionss(new String[]{Manifest.permission.CAMERA}, new PermissionListener() {

                    @Override
                    public void onGranted() {
                        intent.setClass(MainActivity.this, Camera2Activity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onDenied(ArrayList<String> deniedPermission) {
                        ToastUtil.showToast("权限被拒绝了");
                    }
                });

                break;
            case R.id.camerabtn:

                BaseActivity.requestPermissionss(new String[]{Manifest.permission.CAMERA}, new PermissionListener() {

                    @Override
                    public void onGranted() {
                        intent.setClass(MainActivity.this, CameraActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onDenied(ArrayList<String> deniedPermission) {
                        ToastUtil.showToast("权限被拒绝了");
                    }
                });

                break;
            case R.id.tth:
                intent.setClass(this, TTHActivity.class);
                startActivity(intent);
                break;
            case R.id.six_principle:
                intent.setClass(this, PrincipleActivity.class);
                startActivity(intent);
                break;
            case R.id.webview_music:
                intent.setClass(this, WebviewMusicActivity.class);
                startActivity(intent);
                break;
            case R.id.longimage:
                intent.setClass(this, LongImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
