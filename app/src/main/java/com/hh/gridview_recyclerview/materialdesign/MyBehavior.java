package com.hh.gridview_recyclerview.materialdesign;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.hh.gridview_recyclerview.utils.LogUtil;

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

    private static final String TAG = "MyBehavior";

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (dy < 0) {
            ViewCompat.animate(child).scaleX(1).scaleY(1).alpha(1).start();
        } else {
            ViewCompat.animate(child).scaleX(0).scaleY(0).alpha(0).start();
        }
        LogUtil.d(TAG, "dx=" + dx + " dy=" + dy +"consumed = " +consumed[0] +"consumed " +consumed[1]);
    }

//    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//
//        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        if (dyConsumed < 0) {
//            ViewCompat.animate(child).scaleX(1).scaleY(1).alpha(1).start();
//        } else {
//            ViewCompat.animate(child).scaleX(0).scaleY(0).alpha(0).start();
//        }
//        LogUtil.d(TAG, "dxConsumed=" + dxConsumed + " dyConsumed=" + dyConsumed + " dxUnconsumed=" + dxUnconsumed + " dyUnconsumed=" + dyUnconsumed);
//    }
}
