package com.hh.gridview_recyclerview.activity;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.callListener.androidbroadcast.MyPhoneBroadcastListener;
import com.hh.gridview_recyclerview.callListener.androidbroadcast.PhoneStateReceiver;

public class SIMCodeActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static final int REQUEST_READ_PHONE_STATE = 0;
    private TextView text;
    MyPhoneBroadcastListener phoneBroadcastListener;
    PhoneStateReceiver phoneStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simcode);
        text = (TextView) findViewById(R.id.text);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            text.setText(getCode());
        }


        phoneBroadcastListener = new MyPhoneBroadcastListener();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        registerReceiver(phoneBroadcastListener, intentFilter);

        phoneStateReceiver = new PhoneStateReceiver();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("android.intent.action.PHONE_STATE");
        intentFilter1.addAction("android.intent.action.NEW_OUTGOING_CALL");
        registerReceiver(phoneStateReceiver, intentFilter1);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    text.setText(getCode());
                }
                break;
            default:
                break;
        }
    }
    private String getCode() {
        //获取手机的IMSI码
        TelephonyManager telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String imsi = telManager.getSubscriberId();
        String imsi = telManager.getSimCountryIso();
        return imsi;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(phoneBroadcastListener);
        unregisterReceiver(phoneStateReceiver);
    }
}
