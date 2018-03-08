package com.hh.gridview_recyclerview.calendar.common.base.task;

import android.content.Context;
import android.os.AsyncTask;

import com.hh.gridview_recyclerview.calendar.common.listener.OnTaskFinishedListener;


/**
 * Created by Jimmy on 2016/8/24 0024.
 */
public abstract class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    protected Context mContext;
    protected OnTaskFinishedListener<T> mOnTaskFinishedListener;

    public BaseAsyncTask(Context context, OnTaskFinishedListener<T> onTaskFinishedListener) {
        mContext = context;
        mOnTaskFinishedListener = onTaskFinishedListener;
    }

    @Override
    protected abstract T doInBackground(Void... params);

    @Override
    protected void onPostExecute(T data) {
        super.onPostExecute(data);
        if (mOnTaskFinishedListener != null) {
            mOnTaskFinishedListener.onTaskFinished(data);
        }
    }
}
