package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.SharedPreferencesUtil;

public class DragViewActivity extends AppCompatActivity {
    private ImageView draw_imageview;
    private int lastX = 0;
    private int lastY = 0;

    private int startX;
    private int startY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastX = (int) SharedPreferencesUtil.getData(this, "lastX", 0);
        lastY = (int) SharedPreferencesUtil.getData(this, "lastY", 0);
        setContentView(R.layout.activity_drag_view);
        draw_imageview = (ImageView) findViewById(R.id.draw_imageview);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) draw_imageview.getLayoutParams();
        layoutParams.topMargin = lastY;
        layoutParams.leftMargin = lastX;
        draw_imageview.setLayoutParams(layoutParams);

        draw_imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) motionEvent.getRawX();
                        startY = (int) motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int endX = (int) motionEvent.getRawX();
                        int endY = (int) motionEvent.getRawY();

                        int dx = endX - startX;
                        int dy = endY - startY;

                        int l = draw_imageview.getLeft() + dx;
                        int r = draw_imageview.getRight() + dx;
                        int t = draw_imageview.getTop() + dy;
                        int b = draw_imageview.getBottom() + dy;

                        draw_imageview.layout(l, t, r, b);

                        startY = (int) motionEvent.getRawY();
                        startX = (int) motionEvent.getRawX();

                        break;
                    case MotionEvent.ACTION_UP:
                        SharedPreferencesUtil.saveData(DragViewActivity.this, "lastX", draw_imageview.getLeft());
                        SharedPreferencesUtil.saveData(DragViewActivity.this, "lastY", draw_imageview.getTop());
                        break;
                }
                return true;
            }
        });
    }

}
