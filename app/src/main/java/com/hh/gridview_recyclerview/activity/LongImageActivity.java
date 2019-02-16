package com.hh.gridview_recyclerview.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.View.LongImageView;
import com.hh.gridview_recyclerview.utils.BitmapDownload;
import com.hh.gridview_recyclerview.utils.ThreadManager;

import java.io.File;

public class LongImageActivity extends AppCompatActivity {

    private PhotoView photoview;
    private SubsamplingScaleImageView imageView ;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image);
        photoview = (PhotoView) findViewById(R.id.photoview);

        photoview.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549816179474&di=57955b4142a99e92aebc48e155db73d0&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012c1559b2441ea801211d258355d3.jpg%401280w_1l_2o_100sh.png").into(photoview);


//        https://blog.csdn.net/dhl_1986/article/details/55211240
        imageView =  (SubsamplingScaleImageView)findViewById(R.id.imageView);
        File file = new File("/sdcard/360/123.jpg");
        imageView.setImage(ImageSource.uri(Uri.fromFile(file)));

        imageView.setZoomEnabled(true);



        //View view = inflater.inflate(R.layout.content_picture_preview, container, false);
        //final PhotoView photoView = (PhotoView) view.findViewById(R.id.zoom_image_view) ;
        // photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
        //photoView.setBackgroundColor(0xff000000);
        String imagePath = "/sdcard/360/123.jpg";
        imageView =  (SubsamplingScaleImageView)findViewById(R.id.imageView);
        //手势回调
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                    Toast.makeText(getApplicationContext(), "单击: " + ((int) sCoord.x) + ", " + ((int) sCoord.y),
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(getApplicationContext(), fail_tips, Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                    Toast.makeText(getApplicationContext(), "长按: " + ((int) sCoord.x) + ", " + ((int) sCoord.y),
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), fail_tips, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                    Toast.makeText(getApplicationContext(), "双击: " + ((int) sCoord.x) + ", " + ((int) sCoord.y),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

//        final int pos = position;
        // imageView.setMinimumScaleType(Subsamp
        //
        //
        //
        //
        //
        //
        // lingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setMaxScale(15);
        imageView.setZoomEnabled(true);
        // spinner.setVisibility(View.GONE);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        Glide.with(this)
                .load(imagePath).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                ImageSource imageSource = ImageSource.uri(Uri.fromFile(resource));
                int  sWidth = BitmapFactory.decodeFile(resource.getAbsolutePath()).getWidth();
                int sHeight = BitmapFactory.decodeFile(resource.getAbsolutePath()).getHeight();
                WindowManager wm = (WindowManager) LongImageActivity.this
                        .getSystemService(Context.WINDOW_SERVICE);
                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();
//                float scale = SystemUtil.displaySize.x / (float) sWidth;
                //float centerX = SystemUtil.displaySize.x / 2;
                // imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                //imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                if (sHeight >= height
                        && sHeight / sWidth >=3) {
                    imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
                    imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                }else {
                    imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
                    imageView.setImage(ImageSource.uri(Uri.fromFile(resource)));
                    imageView.setDoubleTapZoomStyle(0);
                }
            }});

    }
}
