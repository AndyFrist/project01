package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.hh.gridview_recyclerview.utils.LogUtil;

public class PullableScrollView extends NestedScrollView implements Pullable {
    private static final String TAG = "PullableScrollView";

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
            return true;
        else
            return false;
    }


}
