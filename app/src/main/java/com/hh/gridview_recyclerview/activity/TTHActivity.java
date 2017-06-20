package com.hh.gridview_recyclerview.activity;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.util.Locale;

public class TTHActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextView mytext;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tth);
        mytext = (TextView) findViewById(R.id.mytext);
        textToSpeech = new TextToSpeech(this,this);
    }

    public void click(View v) {
        ToastUtil.showToast(this, "说胡", 3000);
        textToSpeech.speak(mytext.getText().toString().trim(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                ToastUtil.showToast(this, "该语言不支持", 3000);
            }
        }
    }
}
