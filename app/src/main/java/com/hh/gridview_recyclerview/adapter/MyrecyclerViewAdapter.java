package com.hh.gridview_recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.List;

/**
 * Created by 徐小鹏 on 2017/2/28.
 * <p>
 * 注释：
 */

public class MyrecyclerViewAdapter extends RecyclerView.Adapter<MyrecyclerViewAdapter.MyHolder> {
    private List<String> mDatas;
    private LayoutInflater mInflater;

    public MyrecyclerViewAdapter(Context context, List<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder = new MyHolder(mInflater.inflate(R.layout.layout, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mtv);
        }
    }

}
