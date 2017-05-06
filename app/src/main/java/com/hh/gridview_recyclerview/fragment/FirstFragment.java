package com.hh.gridview_recyclerview.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hh.gridview_recyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("Fragment" + "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Fragment" + "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("Fragment" + "onCreateView");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("Fragment" + "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Fragment" + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Fragment" + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("Fragment" + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("Fragment" + "onStop");
    }

}
