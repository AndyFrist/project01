package com.hh.gridview_recyclerview.six_principle.single;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;

/**
 * Created by Administrator on 2017/7/22.
 */

public class SRPImageCache {
    // 图片缓存（内存缓存）
    private LruCache<String, Bitmap> lruCache;

    public SRPImageCache() {
        final int maxMenmory = (int) Runtime.getRuntime().maxMemory() / 1024;
        // 计算缓存内存大小
        int cacheSize = maxMenmory / 4;
        this.lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 计算图片大小
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String imageUrl, Bitmap bitmap) {
        AndFixApplication.lruCache.put(imageUrl, bitmap);
    }

    public Bitmap get(String imageUrl) {
        return AndFixApplication.lruCache.get(imageUrl);
    }
}
