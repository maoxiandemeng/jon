package com.yidong.jon.base;

import android.content.Context;

import com.yidong.jon.MyApplication;
import com.yidong.jon.retrofit.HttpHelper;
import com.yidong.jon.retrofit.ApiRequest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by jon on 2016/12/20
 */

public class BasePresenter<V> {
    public V view;
    protected ApiRequest request;
//    private CompositeSubscription mCompositeSubscription;

    public void attachView(V view) {
        this.view = view;
        request = HttpHelper.getInstance(MyApplication.context).getService(ApiRequest.class);
    }

    public void detachView() {
        this.view = null;
        onUnsubscribe();
    }

    public void addSubscription(Observable observable, Consumer subscriber) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
//        if (mCompositeSubscription == null) {
//            mCompositeSubscription = new CompositeSubscription();
//        }
//        mCompositeSubscription.add(observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber));
    }

    //RXjava取消注册，以避免内存泄露
    private void onUnsubscribe() {
//        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
//            mCompositeSubscription.unsubscribe();
//        }
    }
}
