package com.yidong.jon.widget.loadview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jon on 2016/11/30
 */

public class LoadingLayout extends FrameLayout {
    public static final int LOADING = 0;
    public static final int EMPTY = 1;
    public static final int ERROR = -1;
    public static final int SUCCESS = 2;
    private LoadingConfig loadingConfig;
    private LayoutInflater inflater;
    private View contentView;
    private View loadingView;
    private View emptyView;
    private View errorView;
    private int status;

    private onReloadListener onReloadListener;


    public LoadingLayout(Context context) {
        this(context, null, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) {
            throw new IllegalStateException("LoadingLayout must have only one child");
        }
        contentView = getChildAt(0);
    }

    private void init() {
        loadingView = inflater.inflate(loadingConfig.getLoadingViewId(), null);
        emptyView = inflater.inflate(loadingConfig.getEmptyViewId(), null);
        errorView = inflater.inflate(loadingConfig.getErrorViewId(), null);
        errorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadListener != null) {
                    onReloadListener.onReload();
                }
            }
        });

        addView(loadingView);
        addView(emptyView);
        addView(errorView);
    }

    private void showOnlyView(View showView, View hideView1, View hideView2, View hideView3){
        showView.setVisibility(View.VISIBLE);
        hideView1.setVisibility(View.GONE);
        hideView2.setVisibility(View.GONE);
        hideView3.setVisibility(View.GONE);
    }

    public void setStatus(int status) {
        this.status = status;
        switch (status) {
            case LOADING:
                showOnlyView(loadingView, emptyView, errorView, contentView);
                break;
            case EMPTY:
                showOnlyView(emptyView, loadingView, errorView, contentView);
                break;
            case ERROR:
                showOnlyView(errorView, loadingView, emptyView, contentView);
                break;
            case SUCCESS:
                showOnlyView(contentView, loadingView, emptyView, errorView);
                break;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setLoadingConfig(LoadingConfig loadingConfig) {
        this.loadingConfig = loadingConfig;
        init();
    }

    public void setOnReloadListener(LoadingLayout.onReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    public interface onReloadListener{
        void onReload();
    }
}
