package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.yidong.jon.testdata.TestDataModel;
import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;

import butterknife.BindView;

public class LeakcanaryActivity extends BaseActivity {
    @BindView(R.id.test_view)
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestDataModel.getInstance().setRetainedTextView(testView);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_leakcanary;
    }
}
