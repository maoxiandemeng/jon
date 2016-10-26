package com.yidong.jon.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yidong.jon.model.Adv;
import com.yidong.jon.R;
import com.yidong.jon.base.BaseFragment;
import com.yidong.jon.constant.MyConstant;
import com.yidong.jon.imageloader.ImageLoader;
import com.yidong.jon.imageloader.ImageLoaderUtil;
import com.yidong.jon.retrofit.HttpHelper;
import com.yidong.jon.retrofit.ListResult;
import com.yidong.jon.ui.activity.BehaviorActivity;
import com.yidong.jon.ui.activity.CardPageActivity;
import com.yidong.jon.ui.activity.FristActivity;
import com.yidong.jon.ui.activity.LeakcanaryActivity;
import com.yidong.jon.ui.activity.LoadingActivity;
import com.yidong.jon.ui.activity.StickActivity;
import com.yidong.jon.ui.activity.WaterActivity;
import com.yidong.jon.utils.ApiRequest;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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
        HttpHelper httpHelper = new HttpHelper(getContext());
        ApiRequest service = httpHelper.getService(ApiRequest.class);
        Call<ListResult<Adv>> call = service.getData("3");
        call.enqueue(new Callback<ListResult<Adv>>() {
            @Override
            public void onResponse(Call<ListResult<Adv>> call, Response<ListResult<Adv>> response) {
                Log.i(TAG, "onResponse:"+response.body().getData());
                String pic = response.body().getData().get(0).getAdvSlidePic();
                Log.i(TAG, "onResponse:"+pic);
                tv.setText(pic);
                ImageLoader.Builder url = new ImageLoader.Builder().url(MyConstant.IMAGE_URL + pic).imgView(imageView);
                ImageLoader build = url.build();
                ImageLoaderUtil.getInstance().loadImage(getContext(), build);
//                Glide.with(getActivity()).load(MyConstant.IMAGE_URL + pic).into(imageView);
            }

            @Override
            public void onFailure(Call<ListResult<Adv>> call, Throwable t) {
                Log.i(TAG, "onFailure:"+t.toString());
            }
        });

        Observable<ListResult<Adv>> obData = service.getObData("3");
        obData.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListResult<Adv>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted:");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError:"+e.toString());
                    }

                    @Override
                    public void onNext(ListResult<Adv> advListResult) {
                        Log.i(TAG, "onNext:"+advListResult);
                    }
                });

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
        openActivity(LeakcanaryActivity.class);
    }

}
