package com.hh.gridview_recyclerview.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.activity.BaseActivity;

public class MaterialDesignsActivity extends BaseActivity implements View.OnClickListener {

    private Button material1, material2, material3, material4, material5, material6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_designs);
        material1 = (Button) findViewById(R.id.material1);
        material2 = (Button) findViewById(R.id.material2);
        material3 = (Button) findViewById(R.id.material3);
        material4 = (Button) findViewById(R.id.material4);
        material5 = (Button) findViewById(R.id.material5);
        material6 = (Button) findViewById(R.id.material6);

        material1.setOnClickListener(this);
        material2.setOnClickListener(this);
        material3.setOnClickListener(this);
        material4.setOnClickListener(this);
        material5.setOnClickListener(this);
        material6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == material1) {
            intent.setClass(this, MaterialDesignActivity1.class);
            startActivity(intent);
        }

        if (v == material2) {
            intent.setClass(this, MaterialDesignActivity2.class);
            startActivity(intent);
        }
        if (v == material3) {
            intent.setClass(this, MaterialDesignActivity3.class);
            startActivity(intent);
        }
        if (v == material4) {
            intent.setClass(this, MaterialDesignActivity4.class);
            startActivity(intent);
        }
        if (v == material5) {
            intent.setClass(this, MaterialDesignActivity5.class);
            startActivity(intent);
        }
    }



}

/**
 *
 ****************** MaterialDesign 八大控件 *****************
 *
 *      TabLayout     配合ViewPager使用到达导航的效果
 *
 *      NavigationView  来源自menuItem 实现一个骚效果 参照本项目的TabLauyoutActivity中代码
 *
 *      Snackbar
 *
 *      CoordinatorLayout   可以理解成FrameLayout ，但是可以结合behavior实现一些炫的效果
 *
 *      AppBarLayout extends LinearLayout 就相当于垂直方向上的 LinearLayout
 *
 *      CollapsingToolbarLayout 可折叠的ToolbarLayout
 *
 *      FloatingActionButton 相当于imageButton 是一个悬浮的ImageButton
 *
 *      TextInputLayout  包裹Edittext 提升editext的功能
 *
 *
 *
 *
 *
 */