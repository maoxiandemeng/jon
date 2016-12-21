package com.yidong.jon.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.jon.lib.SdkActivity;
import com.yidong.jon.R;
import com.yidong.jon.base.BaseFragment;
import com.yidong.jon.download.DownloadActivity;
import com.yidong.jon.model.Adv;
import com.yidong.jon.retrofit.ListResult;
import com.yidong.jon.ui.activity.BadgerActivity;
import com.yidong.jon.ui.activity.BehaviorActivity;
import com.yidong.jon.ui.activity.CardPageActivity;
import com.yidong.jon.ui.activity.FlowActivity;
import com.yidong.jon.ui.activity.FristActivity;
import com.yidong.jon.ui.activity.LoadingActivity;
import com.yidong.jon.ui.activity.StickActivity;
import com.yidong.jon.ui.activity.WaterActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";
    public static final String BUNDLE_TITLE = "title";
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.image)
    ImageView imageView;
    private String mTitle = "DefaultValue";
    private Call<ListResult<Adv>> call;

    public static HomeFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
//        if (arguments != null) {
//            mTitle = arguments.getString(BUNDLE_TITLE);
//        }
//        tv.setText(mTitle);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.tv)
    public void onViewPage() {
        openActivity(CardPageActivity.class);
    }

    @OnClick(R.id.behavior)
    public void onBehavior() {
        openActivity(BehaviorActivity.class);
    }

    @OnClick(R.id.stick)
    public void onStick() {
        openActivity(StickActivity.class);
    }

    @OnClick(R.id.anim)
    public void onAnim() {
        openActivity(FristActivity.class);
    }

    @OnClick(R.id.water)
    public void onWater() {
        openActivity(WaterActivity.class);
    }

    @OnClick(R.id.loading)
    public void onLoading() {
        openActivity(LoadingActivity.class);
    }

    @OnClick(R.id.leak)
    public void onLeak() {
        Intent intent = new Intent(getActivity(), SdkActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.download)
    public void onDownload() {
        openActivity(DownloadActivity.class);
    }

    @OnClick(R.id.badger)
    public void onBadger() {
        openActivity(BadgerActivity.class);
    }

    @OnClick(R.id.flow)
    public void onFlow() {
        openActivity(FlowActivity.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call != null){
            call.cancel();
        }
    }
}
