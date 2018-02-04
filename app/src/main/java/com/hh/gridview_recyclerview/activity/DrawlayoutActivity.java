package com.hh.gridview_recyclerview.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.AndroidUtils;

public class DrawlayoutActivity extends AppCompatActivity {

    DrawerLayout drawlayout;
    private LinearLayout content_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawlayout);
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayouttt);
        drawlayout.openDrawer(Gravity.LEFT);    //默认打开左边面板

        content_ll = (LinearLayout) findViewById(R.id.content_ll);
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) content_ll.getLayoutParams();
        layoutParams.topMargin = AndroidUtils.getStatusBarHeight(this);

        View left_rl = findViewById(R.id.left_rl);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        ViewGroup.LayoutParams leftParams = left_rl.getLayoutParams();
        leftParams.height = AndroidUtils.getScreenHeight(this);
        leftParams.width = metric.widthPixels/2;
        left_rl.setLayoutParams(leftParams);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                //将侧边栏顶部延伸至status bar
                drawlayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                drawlayout.setClipToPadding(false);
            }
        }


    }
}
