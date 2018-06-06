package com.hh.gridview_recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.BitmapDownload;
import com.hh.gridview_recyclerview.utils.ThreadManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/7.
 */

public class MyGridVIewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> bitmaps;
    private LayoutInflater inflater;
    private ThreadManager.ThreadPool mThreadPool;
    public MyGridVIewAdapter(Context context, ArrayList<String> bitmaps) {
        this.context = context;
        this.bitmaps = bitmaps;
        inflater = LayoutInflater.from(context);
        mThreadPool = ThreadManager.getThreadPool();
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
//        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layouta, parent, false);
            myViewHolder = new MyViewHolder();
            myViewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_item);
//            convertView.setTag(myViewHolder);
//        }else{
//            myViewHolder = (MyViewHolder) convertView.getTag();
//        }
        mThreadPool.execute(new BitmapDownload(bitmaps.get(position),myViewHolder.imageView));
        return convertView;
    }

    private class  MyViewHolder{
        ImageView imageView;
    }

}
