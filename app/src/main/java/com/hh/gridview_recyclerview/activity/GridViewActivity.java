package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.adapter.AdapterGridView;

import java.util.ArrayList;


public class GridViewActivity extends AppCompatActivity {
    private GridView mygrid_view;
    private AdapterGridView adapterGridView;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        initView();
    }
    private void initView() {
        mygrid_view = (GridView) findViewById(R.id.mygrid_view);
        list = new ArrayList<>();
        for (int i =0;i<70;i++) {
            list.add(i + "个");
        }
        if (list != null && list.size() > 0) {
            adapterGridView = new AdapterGridView(this, list);
        }
        mygrid_view.setAdapter(adapterGridView);
    }
}
