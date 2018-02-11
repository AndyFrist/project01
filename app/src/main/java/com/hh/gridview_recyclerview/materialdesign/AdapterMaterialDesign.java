package com.hh.gridview_recyclerview.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/20.
 */

public class AdapterMaterialDesign extends RecyclerView.Adapter<AdapterMaterialDesign.MyHolder> {
    private Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;

    public AdapterMaterialDesign(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }
}
