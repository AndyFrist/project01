package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.adapter.AdapterRecycler;
import com.hh.gridview_recyclerview.adapter.AdapterStagger;
import com.hh.gridview_recyclerview.recyclerView.DividerItemDecoration;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.util.ArrayList;

public class MyrecycleviewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button r_listview, rh_lsitview, r_gridview, rh_gridview, r_pubu,add,remove;
    private RecyclerView recycle;
    private ArrayList<String> data = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private AdapterRecycler adapterRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecycleview);
        initView();
    }

    private void initView() {
        r_listview = (Button) findViewById(R.id.r_listview);
        rh_lsitview = (Button) findViewById(R.id.rh_lsitview);
        r_gridview = (Button) findViewById(R.id.r_gridview);
        rh_gridview = (Button) findViewById(R.id.rh_gridview);
        r_listview = (Button) findViewById(R.id.r_listview);
        r_pubu = (Button) findViewById(R.id.r_pubu);
        add = (Button) findViewById(R.id.add);
        remove = (Button) findViewById(R.id.remove);
        r_listview.setOnClickListener(this);
        rh_lsitview.setOnClickListener(this);
        r_gridview.setOnClickListener(this);
        rh_gridview.setOnClickListener(this);
        r_listview.setOnClickListener(this);
        r_pubu.setOnClickListener(this);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        recycle = (RecyclerView) findViewById(R.id.recycle);

        for (int i = 0; i < 40; i++) {
            data.add("第" + i);
        }


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapterRecycler = new AdapterRecycler(this, data);

        recycle.setLayoutManager(linearLayoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        adapterRecycler.setOnItemListener(new AdapterRecycler.OnItemListener() {
            @Override
            public void OnItemClickListener(int position) {
                ToastUtil.showToast(MyrecycleviewActivity.this,"点击了" +position,3000);
            }

            @Override
            public void OnItemLongClickListener(int position) {
                ToastUtil.showToast(MyrecycleviewActivity.this,"长按了" +position,3000);
            }
        });
//        recycle.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onClick(View view) {
        //listview
        if (view.getId() == R.id.r_listview) {
            recycle.setAdapter(adapterRecycler);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recycle.setLayoutManager(linearLayoutManager);
        }

        //横向的listview
        if (view.getId() == R.id.rh_lsitview) {
            recycle.setAdapter(adapterRecycler);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recycle.setLayoutManager(linearLayoutManager);
        }
        //GridView
        if (view.getId() == R.id.r_gridview) {
            recycle.setAdapter(adapterRecycler);
            gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
            recycle.setLayoutManager(gridLayoutManager);
        }
        //横向GridView
        if (view.getId() == R.id.rh_gridview) {
            recycle.setAdapter(adapterRecycler);
            gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false);
            recycle.setLayoutManager(gridLayoutManager);
        }
        //瀑布流
        if (view.getId() == R.id.r_pubu) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
            AdapterStagger adapterStagger = new AdapterStagger(this, data);
            recycle.setAdapter(adapterStagger);
            recycle.setLayoutManager(staggeredGridLayoutManager);
        }
        //删除
        if (view.getId() == R.id.remove) {
            data.remove(1);
            adapterRecycler.notifyItemRemoved(1);
        }
        //添加
        if (view.getId() == R.id.add) {
            data.add(1, "add one");
            adapterRecycler.notifyItemInserted(1);
        }
    }
}
