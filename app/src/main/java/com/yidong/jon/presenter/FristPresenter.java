package com.yidong.jon.presenter;

import android.util.Log;

import com.yidong.jon.base.BasePresenter;
import com.yidong.jon.model.VideoEntity;
import com.yidong.jon.retrofit.ApiCallback;
import com.yidong.jon.view.FristView;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jon on 2016/12/20
 */

public class FristPresenter extends BasePresenter<FristView> {
    private static final String TAG = "FristPresenter";
    public FristPresenter(FristView view) {
        attachView(view);
    }

    public void getInfo(HashMap<String, Object> map){
        addSubscription(request.getVideoList(map), new ApiCallback<List<VideoEntity>>() {
            @Override
            public void onSuccess(List<VideoEntity> model) {
                Log.i(TAG, "onSuccess: ");
                view.showVideo(model);
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: ");
            }
        });

        request.getVideo(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
