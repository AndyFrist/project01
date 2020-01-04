package com.hh.gridview_recyclerview.fragment;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hh.gridview_recyclerview.R;

/**
 * @Author: ex-xuxiaopeng002
 * @CreateDate: 2019-07-22 14:59
 * @Description: java类作用描述
 */
public class MyDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        return view;
    }
}
