package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.MySeekBar;

public class MyseekbarActivity extends AppCompatActivity {

    private MySeekBar myseekbar;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myseekbar);
        myseekbar = (MySeekBar) findViewById(R.id.myseekbar);
        textView = (TextView) findViewById(R.id.textView);

        myseekbar.setMax(10);
        myseekbar.setProgress(5);
        myseekbar.setOnMyScrollChangeListener(new MySeekBar.OnMyScrollChangeListener() {
            @Override
            public void onStartChange(int mprogress) {

            }

            @Override
            public void onScrollChange(int mprogress) {
                Log.d("MyseekbarActivity", "mprogress" + mprogress );
                textView.setText(mprogress+"");
            }

            @Override
            public void onStopChange(int mprogress) {
                Log.d("MyseekbarActivity", "停止后 mprogress =" + mprogress );
                textView.setText("停止后" + mprogress);
            }
        });
    }
}
