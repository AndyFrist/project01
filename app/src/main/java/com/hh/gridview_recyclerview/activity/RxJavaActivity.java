package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hh.gridview_recyclerview.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class RxJavaActivity extends AppCompatActivity {
    private TextView rx_tv;
    private Button rx_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        initView();
    }

    private void initView() {
        rx_tv = (TextView) findViewById(R.id.rx_tv);
        rx_btn = (Button) findViewById(R.id.rx_btn);
        rx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Observable.just("你好，RXJava").subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        rx_tv.setText(s);
//                    }
//                });
                //注册观察者活动
                Observable<String> observable = Observable.create(onSubscribe);
                observable.observeOn(AndroidSchedulers.mainThread());       //表示工作在UI线程完成
                observable.subscribe(stringSubscriber);
                observable.subscribe(stringSubscriber2);
            }
        });
    }

    //被观察者
    Observable.OnSubscribe onSubscribe = new Observable.OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("你好，RXANDROID");
            subscriber.onCompleted();
        }
    };
    //观察者1接收事件修改Textview显示
    Subscriber<String> stringSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            rx_tv.setText(s);
        }
    };

    //观察者2
    Subscriber<String> stringSubscriber2 = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };
}
