package com.yidong.jon.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseFragment;
import com.yidong.jon.nestedscroll.NestedScroll2Activity;
import com.yidong.jon.nestedscroll.NestedScrollActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment {
    public static final String BUNDLE_TITLE = "title";

    @BindView(R.id.nested_scroll)
    TextView nestedScroll;

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
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_two;
    }

    @OnClick(R.id.nested_scroll)
    public void onNestedScroll() {
        openActivity(NestedScrollActivity.class);
    }

    @OnClick(R.id.nested_scroll2)
    public void onNestedScroll2() {
        openActivity(NestedScroll2Activity.class);
    }
}
