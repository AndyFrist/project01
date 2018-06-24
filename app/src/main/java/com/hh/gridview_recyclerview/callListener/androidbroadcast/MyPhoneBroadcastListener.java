package com.hh.gridview_recyclerview.callListener.androidbroadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.hh.gridview_recyclerview.myApplication.AndFixApplication;
import com.hh.gridview_recyclerview.utils.SharedPreferencesUtil;

import java.sql.SQLOutput;
import java.util.SortedSet;

/**
 * 系统事件监听器监听器
 *
 * @author ls
 * @version 2013-07-29 8:18
 */
public class MyPhoneBroadcastListener extends BroadcastReceiver {
    /**
     * 手机没有通话，在一般的状态值
     */
    public static final int CALL_TYPE_IDEL = 0;
    /**
     * 手机响铃状态值
     */
    public static final int CALL_TYPE_RING = 1;
    /**
     * 手机通话状态值
     */
    public static final int CALL_TYPE_CALLING = 2;
    /**
     * 当前手机通话状态值
     */
    private int currentState = CALL_TYPE_IDEL;
    /**
     * 手机原来的通话状态值
     */
    private int oldState = CALL_TYPE_IDEL;
    /**
     * 数据库Helper类，只是帮助我们存取状态值，可以改成用文件流实现
     */
    private MyPhoneListener listener;

    @Override//当发生监听的事件，系统会调用这个方法
    public void onReceive(Context context, Intent intent) {
        //进行细节上的监控，我们需要操作TelephonyManager，为它设置监听器，他就给我们反馈
        //拿到系统的TelephonyManager
        TelephonyManager tpManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        listener = new MyPhoneListener();//创建监听器
        tpManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);//设置监听器
    }

    private class MyPhoneListener extends PhoneStateListener {
        @Override//当电话状态发生改变的时候，系统会调用这个方法
        public void onCallStateChanged(int state, String incomingNumber) {
            //首先取得当前的状态值
            oldState = (int) SharedPreferencesUtil.getData(AndFixApplication.getContext(), "PHONESTATUE", currentState);
            System.out.println(""+state);

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    currentState = CALL_TYPE_IDEL;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    currentState = CALL_TYPE_RING;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    currentState = CALL_TYPE_CALLING;
                    break;
            }
            //当通话状态发生改变
            if (oldState == CALL_TYPE_CALLING && currentState == CALL_TYPE_RING) {
                System.out.println("接听");
            } else if (oldState == CALL_TYPE_CALLING && currentState == CALL_TYPE_IDEL) {
                System.out.println("挂断");
            }
            if (oldState == CALL_TYPE_IDEL && currentState == CALL_TYPE_CALLING) {
                System.out.println("拨号");
            }
            SharedPreferencesUtil.saveData(AndFixApplication.getContext(), "PHONESTATUE", currentState);
        }
    }
}
// 闲置 0
// 拨号 2
// 通话