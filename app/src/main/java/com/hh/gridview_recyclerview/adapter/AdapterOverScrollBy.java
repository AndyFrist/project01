package com.hh.gridview_recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.List;

/**
 * Created by huangwenpei on 2018/3/18.
 */

public class AdapterOverScrollBy extends BaseAdapter {

    private Context context;
    private List<String> myList;
    private LayoutInflater mInflater;

    public AdapterOverScrollBy(Context context, List<String> myList) {
        this.context = context;
        this.myList = myList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return myList == null ? 0 : myList.size();
    }

    @Override
    public Object getItem(int i) {
        return myList == null ? null : myList.get(i);
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
            view = mInflater.inflate(R.layout.item_scroll_by, null);
            viewHolder.name = (TextView) view.findViewById(R.id.scroll_name);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(myList.get(i));

        return view;
    }

    class ViewHolder{
        TextView name;
    }
}
