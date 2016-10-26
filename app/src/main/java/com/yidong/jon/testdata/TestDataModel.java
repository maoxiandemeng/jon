package com.yidong.jon.testdata;

import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/10.
 */
public class TestDataModel {
    private static TestDataModel sInstance;
    private TextView mRetainedTextView;

    public static TestDataModel getInstance() {
        if (sInstance == null) {
            sInstance = new TestDataModel();
        }
        return sInstance;
    }

    public void setRetainedTextView(TextView textView) {
        mRetainedTextView = textView;
    }
}
