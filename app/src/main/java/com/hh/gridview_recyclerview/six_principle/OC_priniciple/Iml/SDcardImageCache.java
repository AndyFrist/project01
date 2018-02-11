package com.hh.gridview_recyclerview.six_principle.OC_priniciple.Iml;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.LruCache;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;
import com.hh.gridview_recyclerview.six_principle.OC_priniciple.SaveImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/22.
 */
public class SDcardImageCache implements SaveImage {
    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath() + "/dream/imagecache/";

    /**
     * 缓存图片
     *
     * @param imageUrl
     * @param bitmap
     */
    @Override
    public void put(String imageUrl, Bitmap bitmap) {
        File file1 = new File("/sdcard/dream/imagecache/");
        if (!file1.exists()) {
            file1.mkdirs();
        }
        File file = new File(SDCARD_DIR, "a.jpg");
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, f);
    }

    /**
     * 获取图片
     *
     * @param imageUrl
     * @return
     */
    @Override
    public Bitmap get(String imageUrl) {
        return BitmapFactory.decodeFile(SDCARD_DIR + "a.jpg");
    }
}
