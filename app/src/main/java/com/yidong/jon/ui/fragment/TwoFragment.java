package com.yidong.jon.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.yidong.jon.base.BaseFragment;
import com.yidong.jon.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView tv;
    public static final String BUNDLE_TITLE = "title";
    private String mTitle = "DefaultValue";

    public static TwoFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString(BUNDLE_TITLE);
        }
        tv.setText(mTitle);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }
}
