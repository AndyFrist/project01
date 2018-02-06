package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.LogUtil;

/**
 * HandlerThread的特点
 HandlerThread将loop转到子线程中处理，说白了就是将分担MainLooper的工作量，降低了主线程的压力，使主界面更流畅。
 开启一个线程起到多个线程的作用。处理任务是串行执行，按消息发送顺序进行处理。HandlerThread本质是一个线程，在线程内部，代码是串行处理的。
 但是由于每一个任务都将以队列的方式逐个被执行到，一旦队列中有某个任务执行时间过长，那么就会导致后续的任务都会被延迟处理。
 HandlerThread拥有自己的消息队列，它不会干扰或阻塞UI线程。
 对于网络IO操作，HandlerThread并不适合，因为它只有一个线程，还得排队一个一个等着。
 https://www.cnblogs.com/zhaoyanjun/p/6062880.html
 */

public class SlideActivity extends AppCompatActivity {
    private static final String TAG = "DrawQQLayout";
    private TextView main_tv;
    private HandlerThread myHandlerThread ;
    private Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG,"onCreate");
        setContentView(R.layout.activity_slide);
        main_tv = (TextView) findViewById(R.id.main_tv);
        //创建一个线程,线程名字：handler-thread
        myHandlerThread = new HandlerThread( "handler-thread");
        //开启一个线程
        myHandlerThread.start();
        //在这个线程中创建一个handler对象
        handler = new Handler( myHandlerThread.getLooper() ){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作
                Log.d( "handler " , "消息： " + msg.what + "  线程： " + Thread.currentThread().getName()  ) ;
                main_tv.setText("我是主线程的么~？？？" + Thread.currentThread().getName());
            }
        };

        //在主线程给handler发送消息
        handler.sendEmptyMessage( 1 ) ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程给handler发送数据
                handler.sendEmptyMessage( 2 ) ;
            }
        }).start() ;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源
        myHandlerThread.quit() ;
    }
}
