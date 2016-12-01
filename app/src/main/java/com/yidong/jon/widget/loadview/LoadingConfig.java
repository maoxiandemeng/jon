package com.yidong.jon.widget.loadview;

import com.yidong.jon.R;

/**
 * Created by jon on 2016/11/30
 */

public class LoadingConfig {
    private int emptyViewId;
    private int errorViewId;
    private int loadingViewId;

    public LoadingConfig(Builder builder) {
        this.emptyViewId = builder.emptyViewId;
        this.errorViewId = builder.errorViewId;
        this.loadingViewId = builder.loadingViewId;
    }

    public int getEmptyViewId() {
        return emptyViewId;
    }

    public int getErrorViewId() {
        return errorViewId;
    }

    public int getLoadingViewId() {
        return loadingViewId;
    }

    public static class Builder {
        private int emptyViewId;
        private int errorViewId;
        private int loadingViewId;

        public Builder() {
            this.emptyViewId = R.layout.default_empty_view;
            this.errorViewId = R.layout.default_error_view;
            this.loadingViewId = R.layout.default_loading_view;
        }

        public Builder emptyViewId(int emptyViewId) {
            this.emptyViewId = emptyViewId;
            return this;
        }

        public Builder errorViewId(int errorViewId) {
            this.errorViewId = errorViewId;
            return this;
        }

        public Builder loadingViewId(int loadingViewId) {
            this.loadingViewId = loadingViewId;
            return this;
        }

        public LoadingConfig builder() {
            return new LoadingConfig(this);
        }
    }
}
