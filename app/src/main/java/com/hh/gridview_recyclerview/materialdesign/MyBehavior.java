package com.hh.gridview_recyclerview.materialdesign;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/20.
 */

public class MyBehavior extends CoordinatorLayout.Behavior {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //关心滑动
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed < 0) {
            ViewCompat.animate(child).scaleX(1).scaleY(1).alpha(1).start();
        } else {
            ViewCompat.animate(child).scaleX(0).scaleY(0).alpha(0).start();
        }
    }
}
