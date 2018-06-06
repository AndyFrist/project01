package com.hh.gridview_recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hh.gridview_recyclerview.Bean.Haohan;
import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public class Adaptertable extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> data;

    public Adaptertable(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_table, null);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    class ViewHolder {
        TextView index_num;
        TextView index_name;

    }
}
