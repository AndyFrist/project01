package com.hh.gridview_recyclerview.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.hh.gridview_recyclerview.Bean.Book;
import com.hh.gridview_recyclerview.Bean.RetrofitService;
import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        initView();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<Book> call = service.getSearchBook("金瓶梅", null, 0, 1);

        call.enqueue(new retrofit2.Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, retrofit2.Response<Book> response) {
                text.setText(response.body().toString() + "");
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });

    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
//                //创建okHttpClient对象
//                OkHttpClient mOkHttpClient = new OkHttpClient();
//                //创建一个Request
//                final Request request = new Request.Builder()
//                        .url("https://github.com/hongyangAndroid")
//                        .build();
//                //new call
//                Call call = mOkHttpClient.newCall(request);
//                //请求加入调度
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        ToastUtil.showToast(RetrofitActivity.this,"请求失败",3000);
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String htmlStr = response.body().string();
//                    }
//                });

                break;
        }
    }
}
