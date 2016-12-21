package com.yidong.jon.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.yidong.jon.MyApplication;
import com.yidong.jon.utils.AppUtils;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by jon on 2016/12/20
 */

public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            msg = httpException.getMessage();
            Log.i("info", "code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
        } else {
            msg = e.getMessage();
        }
        if (!AppUtils.isNetworkConnected(MyApplication.context)) {
            msg = "请检查网络连接";
        }
        if (!TextUtils.isEmpty(msg)){
            onFailure(msg);
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
