package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.NumberPickerView;
import com.hh.gridview_recyclerview.View.SwipeLayout;
import com.hh.gridview_recyclerview.utils.ToastUtil;

public class NumberPickActivity extends BaseActivity implements NumberPickerView.OnScrollListener, NumberPickerView.OnValueChangeListener ,View.OnClickListener{
    private static final String TAG = "NumberPickActivity";
    private NumberPickerView picker;
    private String[] mDisplayValues = new String[24];

    private SwipeLayout sl;
    private LinearLayout llllc;
    private TextView tv_name;
    private TextView tt1,tt2,tt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_pick);
        picker = (NumberPickerView) findViewById(R.id.picker);
        picker.setOnScrollListener(this);
        picker.setOnValueChangedListener(this);
        for (int i = 0; i < 24; i++) {
            mDisplayValues[i] = i + "";
        }
        picker.refreshByNewDisplayedValues(mDisplayValues);
        sl = (SwipeLayout) findViewById(R.id.sl);
        llllc = (LinearLayout) findViewById(R.id.llllc);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setOnClickListener(this);
        llllc.setOnClickListener(this);

        tt1 = (TextView) findViewById(R.id.tt1);
        tt1.setOnClickListener(this);
        tt2 = (TextView) findViewById(R.id.tt2);
        tt2.setOnClickListener(this);
        tt3 = (TextView) findViewById(R.id.tt3);
        tt3.setOnClickListener(this);
    }

    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
        String[] content = picker.getDisplayedValues();
        if (content != null) {
            Log.d(TAG, "onValueChange content : " + content[newVal - picker.getMinValue()]);
            Toast.makeText(this, "oldVal : " + oldVal + " newVal : " + newVal + "\n" + "选中内容是" + content[newVal - picker.getMinValue()], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onScrollStateChange(NumberPickerView view, int scrollState) {

    }

    @Override
    public void onClick(View view) {
        if (tv_name == view) {
            ToastUtil.showToast("tv_name");
        }

        if (llllc == view) {
            ToastUtil.showToast("llllc");
        }

        if (tt1 == view) {
            ToastUtil.showToast("tt1");
        }

        if (tt2 == view) {
            ToastUtil.showToast("tt2");
        }
        if (tt3 == view) {
            ToastUtil.showToast("tt3");
        }
    }
}
