package com.yidong.jon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jon on 2016/12/20
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
