package com.hh.gridview_recyclerview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.MyPullListener;
import com.hh.gridview_recyclerview.View.PullToRefreshLayout;
import com.hh.gridview_recyclerview.utils.ToastUtil;


public class MaterialDesignActivity3 extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {
    private AppBarLayout abl_bar;
    private View v_title_big_mask;
    private View v_toolbar_small_mask;
    private View v_toolbar_search_mask;
    private View include_toolbar_search;
    private View include_toolbar_small;
    private int mMaskColor;

    private ImageView iv_plus;
    private ImageView iv_plusss;
    private ImageView siusiu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design3);
        //AppBarLayout
        abl_bar = (AppBarLayout) findViewById(R.id.abl_bar);
        abl_bar.addOnOffsetChangedListener(this);
        //顶部搜索布局
        include_toolbar_search = findViewById(R.id.include_toolbar_search);
        //扫一扫 付一付 聊一聊 咻一咻 的小图标布局
        include_toolbar_small = findViewById(R.id.include_toolbar_small);


        //顶部搜索布局遮罩
        v_toolbar_search_mask = findViewById(R.id.v_toolbar_search_mask);
        //扫一扫 付一付 聊一聊 咻一咻 的大图标布局遮罩
        v_title_big_mask = findViewById(R.id.v_title_big_mask);
        //扫一扫 付一付 聊一聊 咻一咻 的大图标布局遮罩
        v_toolbar_small_mask = findViewById(R.id.v_toolbar_small_mask);
        //背景颜色
        mMaskColor = getResources().getColor(R.color.blue_dark);

        iv_plus = (ImageView) findViewById(R.id.iv_plus);
        iv_plusss = (ImageView) findViewById(R.id.iv_plusss);
        siusiu = (ImageView) findViewById(R.id.siusiu);
        iv_plusss.setOnClickListener(this);
        iv_plus.setOnClickListener(this);
        siusiu.setOnClickListener(this);


    }

    //AppBarLayout的监听方法
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d("aaa", "verticalOffset=" + verticalOffset);
        //720*1080手机 verticalOffset取值范围[0-200]px
        int absVerticalOffset = Math.abs(verticalOffset);//AppBarLayout竖直方向偏移距离px
        int totalScrollRange = appBarLayout.getTotalScrollRange();//AppBarLayout总的距离px
        //背景颜色转化成RGB的渐变色
        int argb = Color.argb(absVerticalOffset, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        int argbDouble = Color.argb(absVerticalOffset * 2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑一半距离后小图标应该由渐变到全透明
        int title_small_offset = (200 - absVerticalOffset) < 0 ? 0 : 200 - absVerticalOffset;
        int title_small_argb = Color.argb(title_small_offset * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑不到一半距离
        if (absVerticalOffset <= totalScrollRange / 2) {
            include_toolbar_search.setVisibility(View.VISIBLE);
            include_toolbar_small.setVisibility(View.GONE);
            //为了和下面的大图标渐变区分,乘以2倍渐变
            v_toolbar_search_mask.setBackgroundColor(argbDouble);
        } else {
            include_toolbar_search.setVisibility(View.GONE);
            include_toolbar_small.setVisibility(View.VISIBLE);
            //appBarLayout上滑一半距离后小图标应该由渐变到全透明
            v_toolbar_small_mask.setBackgroundColor(title_small_argb);

        }
        //上滑时遮罩由全透明到半透明
        v_title_big_mask.setBackgroundColor(argb);
    }

    @Override
    public void onClick(View view) {
        if (view == iv_plusss) {
            ToastUtil.showToast("我才不是原来的加号呢！");
        }

        if (view == iv_plus) {
            ToastUtil.showToast("加号");
        }
        if (view == siusiu) {
            ToastUtil.showToast("siusiu");
        }
    }
}
