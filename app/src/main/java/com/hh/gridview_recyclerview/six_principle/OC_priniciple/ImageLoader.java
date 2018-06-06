package com.hh.gridview_recyclerview.six_principle.OC_priniciple;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.six_principle.single.ImageDownload;
import com.hh.gridview_recyclerview.six_principle.single.SRPImageCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/7/22.
 */

public class ImageLoader {
    private  SaveImage imageCache;
    // 定义线程池
    // 获取CPU核心数:Runtime.getRuntime().availableProcessors()
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader(SaveImage imageCache) {
        this.imageCache = imageCache;
    }

    /**
     * 显示图片
     *
     * @param imageUrl
     * @param bitmap
     */
    public  void displayImage(final Context context, final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        Bitmap bitmap = imageCache.get(imageUrl);
        if (bitmap == null) {
            //通过线程池下载或者显示我们的图片
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    //下载图片
                    final Bitmap bitmap = ImageDownload.download(imageUrl);
                    if (bitmap == null) {
                        return;
                    }
                    if (imageView.getTag().equals(imageUrl)) {
                        //更新UI线程
                        Activity activity = (Activity) context;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                    //缓存图片
                    imageCache.put(imageUrl, bitmap);
                }
            });
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
