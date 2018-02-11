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
 * Created by Administrator on 2017/11/17.
 */

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> data;
    public AdapterRecycler(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name_tv.setText(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener != null) {
                    int pos = holder.getAdapterPosition();
                    onItemListener.OnItemClickListener(pos);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemListener != null) {
                    int pos = holder.getAdapterPosition();
                    onItemListener.OnItemLongClickListener(pos);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
        }
    }

    OnItemListener onItemListener;
    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener{
        void OnItemClickListener(int position);
        void OnItemLongClickListener(int position);
    }
}


