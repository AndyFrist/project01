package com.hh.gridview_recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.hh.gridview_recyclerview.R;


public class WebViewActivity extends AppCompatActivity {
    private LinearLayout webview_root;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview_root = (LinearLayout) findViewById(R.id.webview_root);


        webView = new WebView(this);
        webview_root.addView(webView);


        webView.loadUrl("https://www.baidu.com");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview_root.removeAllViews();
        webView.destroy();
    }
}
