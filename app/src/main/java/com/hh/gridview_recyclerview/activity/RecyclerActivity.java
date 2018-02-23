package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.adapter.MyrecyclerViewAdapter;
import com.hh.gridview_recyclerview.recyclerView.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerview_id;
    private List<String> mDatas;
    private MyrecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerview_id = (RecyclerView) findViewById(R.id.recyclerview_id);
        initData();
        adapter = new MyrecyclerViewAdapter(this,mDatas);
        recyclerview_id.setAdapter(adapter);
        recyclerview_id.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerview_id.addItemDecoration(new DividerGridItemDecoration(this));
    }
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}
