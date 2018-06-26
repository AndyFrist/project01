package com.hh.gridview_recyclerview.callListener.androidbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.hh.gridview_recyclerview.utils.LogUtil;


public class PhoneStateReceiver extends BroadcastReceiver {
    private static final String TAG = "MyPhoneBroadcastListene";
    public final static String B_PHONE_STATE = TelephonyManager.ACTION_PHONE_STATE_CHANGED;
    private OnPhoneStateListener onPhoneStateListener;

    public void setOnPhoneStateListener(OnPhoneStateListener onPhoneStateListener) {
        this.onPhoneStateListener = onPhoneStateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            if (action.equals(B_PHONE_STATE)) {
                doReceivePhone(context, intent);
            }
        } catch (Exception e) {
        }
    }

    public void doReceivePhone(Context context, Intent intent) {
        String phoneNumber = "";
        try {
            phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        } catch (Exception e) {
        }
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int state = telephony.getCallState();
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                if (null != onPhoneStateListener) {
                    onPhoneStateListener.callRinging();
                }
                LogUtil.d(TAG, "[Broadcast]callringing等待接电话=" + phoneNumber);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                LogUtil.d(TAG, "[Broadcast]callringing电话挂断=" + phoneNumber);
                if (null != onPhoneStateListener) {
                    onPhoneStateListener.callOffHook();
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                LogUtil.d(TAG, "[Broadcast]callringing通话中=" + phoneNumber);
                if (null != onPhoneStateListener) {
                    onPhoneStateListener.callRinging();
                }
                break;
        }
    }

    public interface OnPhoneStateListener {
        void callRinging();

        void callOffHook();
    }

}
