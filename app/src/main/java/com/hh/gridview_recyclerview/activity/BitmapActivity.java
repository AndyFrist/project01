package com.hh.gridview_recyclerview.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 *
 高效加载大图   http://wiki.jikexueyuan.com/project/android-training-geek/load-bitmap.html
 */
public class BitmapActivity extends AppCompatActivity {

    private ImageView bigBitmap_im1;
    private ImageView bigBitmap_im2;
    private static Uri uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath()  + "/Heremi/Family/family_header_temp.jpg");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_bitmap);
        bigBitmap_im1 = (ImageView) findViewById(R.id.bigBitmap_im1);
        bigBitmap_im2 = (ImageView) findViewById(R.id.bigBitmap_im2);
        bigBitmap_im1.setImageResource(R.drawable.menu_bg);

        Bitmap bitmap1 = decodeSampledBitmapFromResource(getResources(), R.drawable.aa, 60, 30);
        bigBitmap_im2.setImageBitmap(bitmap1);

    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile("//storage/emulated/0/Heremi/personal_head_temp.jpg", options);
        BitmapFactory.decodeResource(res, resId, options);
        int imageHeight = options.outHeight;
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
//        return BitmapFactory.decodeFile("//storage/emulated/0/Heremi/personal_head_temp.jpg",options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public class CacheSoftRef {

        //首先定义一个HashMap,保存引用对象
        private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

        //再来定义一个方法，保存Bitmap的软引用到HashMap
        public void addBitmapToCache(String path) {
            //强引用的Bitmap对象
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            //软引用的Bitmap对象
            SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
            //添加该对象到Map中使其缓存
            imageCache.put(path, softBitmap);
        }

        //获取的时候，可以通过SoftReference的get()方法得到Bitmap对象
        public Bitmap getBitmapByPath(String path) {
            //从缓存中取软引用的Bitmap对象
            SoftReference<Bitmap> softBitmap = imageCache.get(path);
            //判断是否存在软引用
            if (softBitmap == null) {
                return null;
            }
            //通过软引用取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空，如果未被回收，
            //则可重复使用，提高速度。
            Bitmap bitmap = softBitmap.get();
            return bitmap;
        }
    }
}
