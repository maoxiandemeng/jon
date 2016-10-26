package com.yidong.jon.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView tv;
    public static final String BUNDLE_TITLE = "title";
    private String mTitle = "DefaultValue";

    public static ThreeFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        ThreeFragment fragment = new ThreeFragment();
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
