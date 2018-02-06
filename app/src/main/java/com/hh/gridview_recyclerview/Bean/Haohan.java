package com.hh.gridview_recyclerview.Bean;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public class Haohan implements Comparable<Haohan>{
    private String pinyin;
    private String name;

    public Haohan(String pinyin, String name) {
        this.pinyin = pinyin;
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Haohan haohan) {
        return this.pinyin.compareTo(haohan.getPinyin());
    }
}
