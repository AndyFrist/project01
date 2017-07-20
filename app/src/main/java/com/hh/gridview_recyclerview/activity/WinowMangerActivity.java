package com.hh.gridview_recyclerview.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.FloatView;
import com.hh.gridview_recyclerview.myApplication.AndFixApplication;

public class WinowMangerActivity extends AppCompatActivity {

    private WindowManager mWindowManager;
    private View mLayout;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winow_manger);

        showView();
    }

    private void showView() {
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");

        mLayout = View.inflate(this, R.layout.window_layout, null);
        LinearLayout window_ll = (LinearLayout) mLayout.findViewById(R.id.window_ll);
        window_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWindowManager !=  null && mLayout != null) {
                    mWindowManager.removeView(mLayout);
                    mLayout = null;
                }
            }
        });

        mWindowManager.addView(mLayout, params);// 将view添加在屏幕上(Window)

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在程序退出(Activity销毁）时销毁悬浮窗口
        if (mWindowManager !=  null && mLayout != null) {
            mWindowManager.removeView(mLayout);
            mLayout = null;
        }
    }
}
