package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.myApplication.AndFixApplication;

import java.io.IOException;

public class HotRepairActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String APATCH_PATH = "/fix.apatch"; // 补丁文件名
    private TextView repair_tv;
    private Button repair_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_repair);
        repair_tv = (TextView) findViewById(R.id.repair_tv);
        repair_btn = (Button) findViewById(R.id.repair_btn);
        repair_tv.setText("点击toast");

        repair_tv.setOnClickListener(this);
        repair_btn.setOnClickListener(this);
    }

    private void showToast() {
        Toast.makeText(this, "打补丁之前", Toast.LENGTH_LONG).show();
    }

    /**
     * 动态更新，加载补丁文件
     * @author zehua_chen
     * create at 2016/8/3 14:35
     */
    private void update() {
        String patchFileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
        try {
            AndFixApplication.mPatchManager.addPatch(patchFileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.repair_btn:
                update();
                break;
            case R.id.repair_tv:
                showToast();
                break;
        }
    }
}
