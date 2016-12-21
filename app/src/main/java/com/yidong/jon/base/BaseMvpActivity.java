package com.yidong.jon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jon on 2016/12/20
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity{
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
