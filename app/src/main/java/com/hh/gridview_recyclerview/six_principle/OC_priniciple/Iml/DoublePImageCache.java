package com.hh.gridview_recyclerview.six_principle.OC_priniciple.Iml;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.SaveImage;

/**
 * Created by Administrator on 2017/7/22.
 */

public class DoublePImageCache implements SaveImage {

    private MemoryImageCache memoryImageCache;
    private SDcardImageCache sDcardImageCache;

    public DoublePImageCache() {
        memoryImageCache = new MemoryImageCache();
        sDcardImageCache = new SDcardImageCache();
    }

    public void put(String imageUrl, Bitmap bitmap) {
        memoryImageCache.put(imageUrl, bitmap);
        sDcardImageCache.put(imageUrl, bitmap);
    }

    public Bitmap get(String imageUrl) {
        Bitmap bitmap = memoryImageCache.get(imageUrl);
        if (bitmap == null) {
            bitmap = sDcardImageCache.get(imageUrl);
        }
        return bitmap;
    }
}
