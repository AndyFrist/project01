package com.hh.gridview_recyclerview.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

@RequiresApi(api = Build.VERSION_CODES.M)
public class InputActivity extends BaseActivity implements View.OnScrollChangeListener {
    private ScrollView scrollView;
    private TextView textView;
    private PopupWindow popupWindow;

    private int orginalX, originalY;

    private int popWidth, popHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            this.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        textView = (TextView) findViewById(R.id.tv);

        scrollView.setOnScrollChangeListener(this);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            showPopAlongView(textView);
        }
    }

    private void showPopAlongView(View v) {
        View view = View.inflate(this, R.layout.pop_layout, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int measureHeight = view.getMeasuredHeight();
        int measureWidth = view.getMeasuredWidth();
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getMeasuredWidth() / 2 - measureWidth / 2, location[1] - measureHeight);

        orginalX = location[0] + v.getMeasuredWidth() / 2 - measureWidth / 2;
        originalY = location[1] - measureHeight;

        popWidth = measureWidth;
        popHeight = measureHeight;
    }


    /**
     * Called when the scroll position of a view changes.
     *
     * @param v          The view whose scroll position has changed.
     * @param scrollX    Current horizontal scroll origin.
     * @param scrollY    Current vertical scroll origin.
     * @param oldScrollX Previous horizontal scroll origin.
     * @param oldScrollY Previous vertical scroll origin.
     */
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int currentY = originalY - scrollY;
        popupWindow.update(orginalX, currentY, popWidth, popHeight);
    }
}
