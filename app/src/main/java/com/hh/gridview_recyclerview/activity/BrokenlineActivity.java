package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.CalendarView;

public class BrokenlineActivity extends AppCompatActivity {
    private static final String TAG = "BrokenlineActivity";

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brokenline);


        calendarView = (CalendarView) findViewById(R.id.calendarview);
        calendarView.setDate("2016-12-31");
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day) {
                Toast.makeText(BrokenlineActivity.this, year+"=" + month + "=" + day, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
