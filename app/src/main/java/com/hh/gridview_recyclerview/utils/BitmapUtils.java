package com.hh.gridview_recyclerview.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by huangwenpei on 2017/12/17.
 */

public class BitmapUtils {

    private volatile static BitmapUtils bitmapUtils = null;
    private LruCache<String,Bitmap> myLurcatch;
    public  static BitmapUtils getInstance(){
        if (bitmapUtils != null) {
//            synchronized (BitmapUtils.class)
            if (bitmapUtils == null) {
                bitmapUtils = new BitmapUtils();
            }
        }
        return bitmapUtils;
    }

    private BitmapUtils(){
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
        myLurcatch = new LruCache<String, Bitmap>(maxSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf(key, value);
            }
        };
    }

    public void putImage(String url,Bitmap bitmap){
        myLurcatch.put(url, bitmap);
    }

    public Bitmap getImage(String url) {
        return myLurcatch.get(url);
    }
}
