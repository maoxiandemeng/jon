package com.yidong.jon.retrofitdownload.download;

import com.yidong.jon.retrofitdownload.download.db.DownLoadEntity;

public interface DownLoadBackListener {
    void onStart(double percent);

    void onCancel();

    void onDownLoading(double percent);

    void onCompleted();

    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}
