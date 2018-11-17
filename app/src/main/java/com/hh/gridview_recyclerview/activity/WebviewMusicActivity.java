package com.hh.gridview_recyclerview.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ImoocJsInterfave;

public class WebviewMusicActivity extends AppCompatActivity {

    private WebView my_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_music);
        my_webview = (WebView) findViewById(R.id.mWebView);
        //允许webview加载JS代码
        my_webview.getSettings().setJavaScriptEnabled(true);
        //给webview添加JS接口
//        my_webview.addJavascriptInterface(new ImoocJsInterfave(this), "imoocLauncher");

        my_webview.loadUrl("https://test-icore-pts-mobile.pingan.com.cn/iCorePts-mobile/index.html#/");
//        my_webview.loadUrl("http://www.w3school.com.cn/i/horse.mp3");
        my_webview.getSettings().setMediaPlaybackRequiresUserGesture(true);
        my_webview.setWebContentsDebuggingEnabled(true);

        //从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式。
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            my_webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        my_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {


                return super.shouldOverrideUrlLoading(view, request);
            }
        });


//        my_webview.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                title_view.setText(title);
//            }
//        });
    }
}
