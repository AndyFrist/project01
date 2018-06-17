package com.hh.gridview_recyclerview.utils;

import android.webkit.JavascriptInterface;

/**
 * Created by huangwenpei
 * on 2018/6/6.
 */

public class ImoocJsInterfave {
    private static final String TAG = "ImoocJsInterfave";
    private ImoocJsInterfaceBridge bridge;

    public ImoocJsInterfave(ImoocJsInterfaceBridge bridge) {
        this.bridge = bridge;
    }

    /**
     * 此方法不在主线程执行
     * @param value
     */

    @JavascriptInterface
    public void setValue(String value){
        LogUtil.d(TAG, value);
        bridge.setValues(value);
    }

}

//js交互中常见的一些错误
//https://www.imooc.com/video/17018

//1、在JS接口的回调方法setValue()中 throw exception  H5中会报异常，APP并不会崩溃
//2、web端不进行对象存在的判断 会报异常H5
//3、传递参数类型不一致（尤其是数组）js中数组可以是不同类型，Java是不支持的
//4、字符串类型参数为空时传递undefined