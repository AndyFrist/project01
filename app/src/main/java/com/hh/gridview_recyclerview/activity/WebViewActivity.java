package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;


public class WebViewActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout webview_root;
    private WebView my_webview;
    
    private TextView back;
    private TextView title_view;
    private TextView flash;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview_root = (LinearLayout) findViewById(R.id.webview_root);
        init();
    }

    private void init() {
        back = (TextView) findViewById(R.id.back);
        flash = (TextView) findViewById(R.id.flash);
        title_view = (TextView) findViewById(R.id.title);
        back.setOnClickListener(this);
        flash.setOnClickListener(this);
        my_webview = (WebView) findViewById(R.id.my_webview);
        my_webview.getSettings().setJavaScriptEnabled(true);
        my_webview.loadUrl("https://www.baidu.com/");

        my_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {


                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        my_webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                title_view.setText(title);
            }
        });

        my_webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view == flash){
            my_webview.reload();
        }
        if (view == back){
            finish();
        }
    }
}
