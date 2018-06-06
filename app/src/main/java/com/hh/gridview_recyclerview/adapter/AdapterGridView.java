package com.hh.gridview_recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

/**
 * Created by 徐小鹏 on 2017/1/5.
 * <p>
 * 注释：
 */

public class AdapterGridView extends BaseAdapter {
    private Context context;
    private ArrayList<String> mList;
    private LayoutInflater mInflater;

    public AdapterGridView(Context context, ArrayList<String> mList) {
        this.context = context;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = mInflater.inflate(R.layout.item_gridview, null);
            holder.imageView = (ImageView) view.findViewById(R.id.grid_img);
            holder.textView = (TextView) view.findViewById(R.id.grid_tv);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.textView.setText(mList.get(i));
        switch (i % 9) {
            case 0:
                holder.imageView.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case 1:
                holder.imageView.setBackgroundResource(R.mipmap.speed_one);
                break;
            case 2:
                holder.imageView.setImageResource(R.mipmap.speed_two);
                break;
            case 3:
                holder.imageView.setImageResource(R.mipmap.speed_three);
                break;
            case 4:
                holder.imageView.setImageResource(R.mipmap.step);
                break;
            case 5:
                holder.imageView.setImageResource(R.mipmap.y_above_mark);
                break;
            case 6:
                holder.imageView.setImageResource(R.mipmap.y_below_mark);
                break;
            case 7:
                holder.imageView.setImageResource(R.mipmap.y_left_mark);
                break;
            case 8:
                holder.imageView.setImageResource(R.mipmap.y_above_mark);
                break;

        }
        return view;
    }

    class Holder {
        ImageView imageView;
        TextView textView;
    }
}
