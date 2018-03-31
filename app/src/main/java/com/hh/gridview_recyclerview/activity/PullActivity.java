package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ToastUtil;

public class PullActivity extends AppCompatActivity implements View.OnClickListener{
private TextView pull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        pull = (TextView) findViewById(R.id.pull);
        pull.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == pull) {
            ToastUtil.showToast("pull");
        }
    }
}
