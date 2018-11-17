package com.hh.gridview_recyclerview.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ImoocJsInterfaceBridge;
import com.hh.gridview_recyclerview.utils.ImoocJsInterfave;
import com.hh.gridview_recyclerview.utils.LogUtil;


public class WebViewActivity extends AppCompatActivity implements View.OnClickListener ,ImoocJsInterfaceBridge {
    private static final String TAG = "WebViewActivity";
    private LinearLayout webview_root;
    private WebView my_webview;

    private TextView back;
    private TextView title_view;
    private TextView flash;


    private EditText edittext;
    private Button send2webview;

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
        edittext = (EditText) findViewById(R.id.edittext);
        send2webview = (Button) findViewById(R.id.send2webview);

        back.setOnClickListener(this);
        flash.setOnClickListener(this);
        send2webview.setOnClickListener(this);

        my_webview = (WebView) findViewById(R.id.my_webview);
        //允许webview加载JS代码
        my_webview.getSettings().setJavaScriptEnabled(true);
        //给webview添加JS接口
        my_webview.addJavascriptInterface(new ImoocJsInterfave(this), "imoocLauncher");

        my_webview.loadUrl("file:///android_asset/webview.html");


        my_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {


                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        my_webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                title_view.setText(title);
            }
        });

        my_webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                LogUtil.d(TAG,"下载进度"+ l);
            }
        });

        //允许浏览器调试
        my_webview.setWebContentsDebuggingEnabled(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view == flash) {
            my_webview.reload();
        }
        if (view == back) {
            finish();
        }
        if (view == send2webview) {
            String str = edittext.getText().toString().trim();
            //Android调用js代码传值给H5
            my_webview.loadUrl("javascript:if(window.remote){window.remote('" + str + "')}");
        }
    }

    @Override
    public void setValues(final String values) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edittext.setText(values);
            }
        });
    }
}
