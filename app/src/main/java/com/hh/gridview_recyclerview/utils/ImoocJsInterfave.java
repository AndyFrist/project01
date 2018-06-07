package com.hh.gridview_recyclerview.utils;

import android.webkit.JavascriptInterface;

/**
 * Created by huangwenpei
 * on 2018/6/6.
 */

public class ImoocJsInterfave {
    private static final String TAG = "ImoocJsInterfave";

    @JavascriptInterface
    public void setValue(String value){
        LogUtil.d(TAG, value);
    }

}
