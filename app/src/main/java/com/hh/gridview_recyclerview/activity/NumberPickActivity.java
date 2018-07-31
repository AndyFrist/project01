package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.NumberPickerView;
import com.hh.gridview_recyclerview.View.SwipeLayout;
import com.hh.gridview_recyclerview.dialog.DateDialog;
import com.hh.gridview_recyclerview.utils.AndroidUtils;

import java.util.ArrayList;
import java.util.Collections;

public class NumberPickActivity extends BaseActivity implements NumberPickerView.OnScrollListener, NumberPickerView.OnValueChangeListener{
    private static final String TAG = "NumberPickActivity";
    private NumberPickerView picker;


    private ArrayList<String> startYear = new ArrayList<>();
    private ArrayList<String> startMonth = new ArrayList<>();
    private ArrayList<String> startDay = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_pick);

        //初始化年
        startYear.add(AndroidUtils.getYear() + "");

        //初始化月
        for (int i = 0; i < 4; i++) {
            int currentMonth = AndroidUtils.getMonth();
            startMonth.add((currentMonth - i) % 13 + "");
        }

        //初始化天
        for (int i = 0; i < 31; i++) {
            int currentDay = AndroidUtils.getDay();
            startDay.add((currentDay - i) % 32 + "");
        }

        Collections.reverse(startYear);
        Collections.reverse(startMonth);
        Collections.reverse(startDay);

        DateDialog dateDialog = new DateDialog(this);
        dateDialog.setData(
                startYear.toArray(new String[startYear.size()]),
                startMonth.toArray(new String[startMonth.size()]),
                startDay.toArray(new String[startDay.size()]));






        picker = (NumberPickerView) findViewById(R.id.picker);
        picker.setOnScrollListener(this);
        picker.setOnValueChangedListener(this);
        picker.refreshByNewDisplayedValues(startMonth.toArray(new String[startMonth.size()]));


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
