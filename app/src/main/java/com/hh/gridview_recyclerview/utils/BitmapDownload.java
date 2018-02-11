package com.hh.gridview_recyclerview.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/3/7.
 */

public class BitmapDownload implements Runnable {
    private String path;
    private ImageView imageView;

    public BitmapDownload(String path, ImageView imageView) {
        this.path = path;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        Bitmap bitmapFromMemCache = MyLruCache.getBitmapFromMemCache(path);
        try {
            if (bitmapFromMemCache == null) {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() == 200) {
                    InputStream inputStream = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                    MyLruCache.addBitmapToMemoryCache(path, bitmap);
                }
            } else {
                imageView.setImageBitmap(bitmapFromMemCache);
            }
        } catch (Exception e) {
            Log.i("", e.toString());
        }
    }
}
