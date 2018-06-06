package com.hh.gridview_recyclerview.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.PraiseModel;
import com.hh.gridview_recyclerview.View.PraiseSurfaceView;

/**
 * Created by dillon on 2017/5/11.
 */

public class TestPraiseActivity extends Activity implements View.OnTouchListener {

    private int x, y;//屏幕点击位置
    private int count = 0;
    private long firClick = 0;
    private long secClick = 0;
    private final int interval = 1500;//两次点击时间间隔，单位毫秒
    private PraiseSurfaceView praiseSurfaceView;

    public static void actionStart(Context context, boolean isFromClickNotification) {
        Intent intent = new Intent(context, TestPraiseActivity.class);
        if (isFromClickNotification) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise_test);
        initView();
        initListener();
    }


    private void initListener() {
        praiseSurfaceView.setOnTouchListener(this);
    }

    private void initView() {
        praiseSurfaceView = (PraiseSurfaceView) findViewById(R.id.sfv_praise);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            count++;
            if (1 == count) {//单击效果
                switch (v.getId()) {
                    case R.id.sfv_praise:
                        PraiseModel praiseModel = new PraiseModel(praiseSurfaceView, x, y, 0,1000);
                        praiseSurfaceView.addPraise(praiseModel);
                        break;
                }
                firClick = System.currentTimeMillis();
            } else if (2 == count) {//双击效果
                x = (int) event.getX();
                y = (int) event.getY();
                secClick = System.currentTimeMillis();
                if (secClick - firClick < interval) {
                    switch (v.getId()) {
                        case R.id.sfv_praise:
                            PraiseModel praiseModel = new PraiseModel(praiseSurfaceView, x, y, 1,1000);
                            praiseSurfaceView.addPraise(praiseModel);
                            break;
                    }
                    count = 0;
                    firClick = 0;
                } else {
                    firClick = secClick;
                    count = 1;
                }
                secClick = 0;
            }
        }
        return true;
    }
}
