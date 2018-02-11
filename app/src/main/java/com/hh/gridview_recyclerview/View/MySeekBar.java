package com.hh.gridview_recyclerview.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

public class MySeekBar extends RelativeLayout {

    private Context context;
    private Bitmap centerImage;
    private Bitmap seekBarImage;

    private HorizontalScrollView myseekscrollview;
    private ImageView scrollimg;
    private TextView up_view, down_view;
    private int max = 100;
    private int mprogress = 0;
    private float ratio;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public MySeekBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.myseekbar, this, true);
        myseekscrollview = (HorizontalScrollView) view.findViewById(R.id.myseekscrollview);
        scrollimg = (ImageView) view.findViewById(R.id.scrollimg);
        up_view = (TextView) view.findViewById(R.id.up_view);
        down_view = (TextView) view.findViewById(R.id.down_view);

        myseekscrollview.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mprogress = (int) (scrollX * ratio);
                if (mprogress > max) {
                    mprogress = max;
                } else if (mprogress < 0) {
                    mprogress = 0;
                }
                onMyScrollChangeListener.onScrollChange(mprogress);
            }
        });

        myseekscrollview.setOnTouchListener(new OnTouchListener() {
            private int lastX = 0;
            private int touchEventId = -9983761;
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastX == scroller.getScrollY()) {
                            handleStop(scroller);
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 5);
                            lastX = scroller.getScrollY();
                        }
                    }
                }
            };
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    onMyScrollChangeListener.onStartChange(mprogress);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }
            private void handleStop(Object view) {
                Log.d("MySeekBar", "停止了 mprogress = " + mprogress );
                onMyScrollChangeListener.onStopChange(mprogress);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) up_view.getLayoutParams();
        layoutParams.width = r / 2;
        up_view.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) down_view.getLayoutParams();
        Params.width = r / 2;
        down_view.setLayoutParams(Params);

        LinearLayout.LayoutParams scrollimgLayoutParams = (LinearLayout.LayoutParams) scrollimg.getLayoutParams();
        scrollimgLayoutParams.width = r;
        scrollimg.setLayoutParams(scrollimgLayoutParams);

        ratio = (float) max / (float) r;
    }

    /**
     * 设置最大值,用于设置一个系数
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 设置进度的值
     *
     * @param progress
     */
    public void setProgress(final int progress) {
        this.mprogress = progress;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myseekscrollview.scrollTo((int) (mprogress / ratio),0);
            }
        }, 1000);
    }

    /**
     * 获取 mprogress
     *
     * @return
     */
    public int getMprogress() {
        return mprogress;
    }

    public interface OnMyScrollChangeListener {
        public void onStartChange(int mprogress);

        public void onScrollChange(int mprogress);

        public void onStopChange(int mprogress);
    }

    private OnMyScrollChangeListener onMyScrollChangeListener;

    public void setOnMyScrollChangeListener(OnMyScrollChangeListener listener) {
        onMyScrollChangeListener = listener;
    }
}
