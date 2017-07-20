package com.hh.gridview_recyclerview.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ToastUtil;

public class SwipeRefreshLayoutActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

//        初始化下拉刷新控件
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        mSwipeLayout.setOnRefreshListener(this);

// 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新

        mSwipeLayout.setProgressBackgroundColor(R.color.color_item_press); // 设定下拉圆圈的背景

        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小
    }

    @Override
    public void onRefresh() {
        ToastUtil.showToast(this,"触发刷新",5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                mSwipeLayout.setRefreshing(false);
            }
        }, 5000); // 5秒后发送消息，停止刷新
    }
}
