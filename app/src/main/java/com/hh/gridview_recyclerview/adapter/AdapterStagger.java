package com.hh.gridview_recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/18.
 */

public class AdapterStagger extends RecyclerView.Adapter<AdapterStagger.MyHolderView> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> data;
    private ArrayList<Integer> mHight = new ArrayList<>();

    public AdapterStagger(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < data.size(); i++) {
            mHight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        AdapterStagger.MyHolderView myViewHolder = new AdapterStagger.MyHolderView(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyHolderView holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHight.get(position);
        holder.itemView.setLayoutParams(layoutParams);

        holder.name_tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolderView extends RecyclerView.ViewHolder {
        TextView name_tv;

        public MyHolderView(View itemView) {
            super(itemView);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }
}
