package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ToastUtil;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
    }

    public void click(View view){
        ToastUtil.showToast(this,"hahhahhh",3000);
    }
}
