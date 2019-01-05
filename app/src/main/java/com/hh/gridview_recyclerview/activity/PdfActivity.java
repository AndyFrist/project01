package com.hh.gridview_recyclerview.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.hh.gridview_recyclerview.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class PdfActivity extends AppCompatActivity {
    String pdf = "http://beta.juzhennet.com/dtkj10/file/upload/2016/11/07/1478680611.pdf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        String path = Environment.getExternalStorageDirectory().getPath() + File.separator;
        RequestParams requestParams = new RequestParams(pdf);
        requestParams.setSaveFilePath(path);

        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                System.out.println("----" + total + "------" + current);
            }

            @Override
            public void onSuccess(File result) {
                test(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    private void test(File file) {

        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
//        pdfView.fromUri(Uri)
//        or
        pdfView.fromFile(file)
//        or
//        pdfView.fromBytes(byte[])
//        or
//        pdfView.fromStream(InputStream)
//        or
//        pdfView.fromSource(DocumentSource)
//        or
//        pdfView.fromAsset(s)
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
//                .onDraw(onDrawListener)
//                .onLoad(onLoadCompleteListener)
//                .onPageChange(onPageChangeListener)
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }

}
