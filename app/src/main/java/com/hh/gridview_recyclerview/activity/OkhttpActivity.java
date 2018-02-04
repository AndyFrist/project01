package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.LogUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * 慕课网鸿洋视频
 * 自己写strut服务器
 */
public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "OkhttpActivity";
    private String baseURL = "http://192.168.0.112:8080/";
    private Button get1, post, poststring, postfile, uploadfile, download, service;
    private TextView text_view;
    private OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        get1 = (Button) findViewById(R.id.get1);
        get1.setOnClickListener(this);
        post = (Button) findViewById(R.id.post);
        post.setOnClickListener(this);
        poststring = (Button) findViewById(R.id.poststring);
        poststring.setOnClickListener(this);
        postfile = (Button) findViewById(R.id.postfile);
        postfile.setOnClickListener(this);
        uploadfile = (Button) findViewById(R.id.uploadfile);
        uploadfile.setOnClickListener(this);
        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);
        service = (Button) findViewById(R.id.service);
        service.setOnClickListener(this);
        text_view = (TextView) findViewById(R.id.text_view);


        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.get1) {
            //1拿到OkHttpClient 对象
            //2构造Requst
            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url(baseURL + "imooc/login?name=shuaige&password=123").build();

            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    LogUtil.d(TAG, response.body().string());
                }
            });
        }

        if (view.getId() == R.id.post) {
            //1拿到OkHttpClient 对象
            //2构造Requst
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            RequestBody requestBody = formEncodingBuilder.add("name", "帅哥").add("password", "123321").build();

            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url(baseURL + "imooc/login").post(requestBody).build();
            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    LogUtil.d(TAG, response.body().string());
                }
            });
        }
        if (view.getId() == R.id.poststring) {
            //1拿到OkHttpClient 对象
            //2构造Requst

            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;chaset=utf-8"), "{name:帅锅,password:123}");
            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url(baseURL + "imooc/postString").post(requestBody).build();
            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    LogUtil.d(TAG, response.body().string());
                }
            });
        }
        if (view.getId() == R.id.postfile) {
            //1拿到OkHttpClient 对象
            //2构造Requst

            File file = new File(Environment.getExternalStorageDirectory(), "aa.jpg");
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url(baseURL + "imooc/postFile").post(requestBody).build();
            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    LogUtil.d(TAG, response.body().string());
                }
            });
        }

        if (view.getId() == R.id.uploadfile) {
            //1拿到OkHttpClient 对象
            //2构造Requst

            File file = new File(Environment.getExternalStorageDirectory(), "aa.jpg");

            MultipartBuilder multipartBuilder = new MultipartBuilder();
            RequestBody requestBody = multipartBuilder
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart("name", "帅帅")
                    .addFormDataPart("password", "123")
                    .addFormDataPart("mPhoto", "photo.jpg", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url(baseURL + "imooc/uploadFile").post(requestBody).build();
            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    LogUtil.d(TAG, response.body().string());
                }
            });
        }
        if (view.getId() == R.id.download) {
            //1拿到OkHttpClient 对象
            //2构造Requst
            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url(baseURL + "imooc/files/aa.jpg").build();
            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    InputStream inputStream = response.body().byteStream();
                    File file = new File(Environment.getExternalStorageDirectory(), "7897.jpg");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] buf = new byte[1024];
                    while ((len = inputStream.read(buf)) != -1) {
                        fileOutputStream.write(buf, 0, len);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            });
        }
        if (view.getId() == R.id.service) {
            //1拿到OkHttpClient 对象
            //2构造Requst
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            RequestBody requestBody = formEncodingBuilder
                    .add("req_from", "mj-app")
//                    .add("jd", "115.607708")
//                    .add("wd", "27.93405")
//                    .add("platfrom", "android")
//                    .add("user_login_name", "13507819609")
                    .add("data", "{\"jd\":\"115.607708\",\"wd\":\"27.934054\",\"user_login_name\":\"13507819609\",\"user_login_pwd\":\"12345678\",\"platfrom\":\"android\"}")
                    .build();

            Request.Builder builder = new Request.Builder();
            final Request request = builder.get().url("http://192.168.0.148:8080/ALT/login_byAppOrdinaryUser.action?").post(requestBody).build();
            //3将Request封装为call
            Call call = okHttpClient.newCall(request);
            //4执行Call
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    LogUtil.d(TAG, "失败");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    LogUtil.d(TAG, response.body().string());
                }
            });
        }
    }
}
