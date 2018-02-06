package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.NumberPickerView;

public class NumberPickActivity extends BaseActivity implements NumberPickerView.OnScrollListener, NumberPickerView.OnValueChangeListener {
    private static final String TAG = "NumberPickActivity";
    private NumberPickerView picker;
    private String[] mDisplayValues = new String[24];

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
}
