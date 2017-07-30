package com.hh.gridview_recyclerview.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.fragment.MyMapFragment;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyMapFragment fragment = new MyMapFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.map_fl_content,fragment);
        transaction.commit();
    }
}
