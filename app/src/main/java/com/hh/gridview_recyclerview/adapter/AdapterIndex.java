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

public class AdapterIndex extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Haohan> data;

    public AdapterIndex(Context context, ArrayList<Haohan> data) {
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
            view = inflater.inflate(R.layout.item_index, null);
            viewHolder.index_num = (TextView) view.findViewById(R.id.index_num);
            viewHolder.index_name = (TextView) view.findViewById(R.id.index_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (i > 0 && data.get(i).getPinyin().charAt(0) == data.get(i - 1).getPinyin().charAt(0)) {
            viewHolder.index_num.setVisibility(View.GONE);
        } else {
            viewHolder.index_num.setVisibility(View.VISIBLE);
            viewHolder.index_num.setText(data.get(i).getPinyin().charAt(0)+"");
        }
        viewHolder.index_name.setText(data.get(i).getName());
        return view;
    }

    class ViewHolder {
        TextView index_num;
        TextView index_name;

    }
}
