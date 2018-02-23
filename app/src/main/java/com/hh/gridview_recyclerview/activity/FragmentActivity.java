package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.fragment.FirstFragment;
import com.hh.gridview_recyclerview.utils.SlidingActivity;

public class FragmentActivity extends SlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        System.out.println("Activity" + "onCreate");
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FirstFragment firstFragment = new FirstFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content,firstFragment);
        transaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Activity" + "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Activity" + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Activity" + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Activity" + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Activity" + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Activity" + "onDestroy");
    }
}
