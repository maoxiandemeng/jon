package com.yidong.jon.presenter;

import android.util.Log;

import com.yidong.jon.base.BasePresenter;
import com.yidong.jon.model.VideoEntity;
import com.yidong.jon.retrofit.ApiCallback;
import com.yidong.jon.retrofit.ListResult;
import com.yidong.jon.retrofit.Result;
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
//        addSubscription(request.getVideoList(map), new ApiCallback<List<VideoEntity>>() {
//
//            @Override
//            public void onSuccess(List<VideoEntity> model) {
//                Log.i(TAG, "onSuccess: ");
//                view.showVideo(model);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//        request.getVideoList(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ApiCallback<List<VideoEntity>>() {
//                    @Override
//                    public void onSuccess(List<VideoEntity> model) {
//                        Log.i(TAG, "onSuccess2: ");
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//                });
//        addSubscription(request.getVideoList(map), new ApiCallback<List<VideoEntity>>() {
//            @Override
//            public void onSuccess(List<VideoEntity> model) {
//                Log.i(TAG, "onSuccess: ");
//                view.showVideo(model);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                Log.i(TAG, "onFailure: ");
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i(TAG, "onFinish: ");
//            }
//        });

//        addSubscription(request.getVideoList2(map), new ApiCallback<Result<List<VideoEntity>>>() {
//
//
//            @Override
//            public void onSuccess(Result<List<VideoEntity>> model) {
//                model.getCode();
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });

//        request.getVideo(map).enqueue(new Callback<List<VideoEntity>>() {
//            @Override
//            public void onResponse(Call<List<VideoEntity>> call, Response<List<VideoEntity>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<VideoEntity>> call, Throwable t) {
//
//            }
//        });

    }
}
