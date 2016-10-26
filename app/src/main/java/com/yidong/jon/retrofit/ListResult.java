package com.yidong.jon.retrofit;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/12.
 */
public class ListResult<T> extends BaseResult {
    private ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
