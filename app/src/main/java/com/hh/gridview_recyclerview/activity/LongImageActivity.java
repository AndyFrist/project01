package com.hh.gridview_recyclerview.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.LongImageView;
import com.hh.gridview_recyclerview.utils.BitmapDownload;
import com.hh.gridview_recyclerview.utils.ThreadManager;

public class LongImageActivity extends AppCompatActivity {

    private PhotoView photoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image);
        photoview = (PhotoView) findViewById(R.id.photoview);

        photoview.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549816179474&di=57955b4142a99e92aebc48e155db73d0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012c1559b2441ea801211d258355d3.jpg%401280w_1l_2o_100sh.png").into(photoview);

//        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549816179474&di=57955b4142a99e92aebc48e155db73d0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012c1559b2441ea801211d258355d3.jpg%401280w_1l_2o_100sh.png";
//
//
//        Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//
//                Drawable drawable = resource;
//
//                BitmapDrawable bd = (BitmapDrawable) drawable;
//
//                Bitmap bitmap = bd.getBitmap();
//
//                photoview.setBitmap(bitmap);
//
//
//                return false;
//            }
//        });

//        photoview.setBitmap();
    }
}
