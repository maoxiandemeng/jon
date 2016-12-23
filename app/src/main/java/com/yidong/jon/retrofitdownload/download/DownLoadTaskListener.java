package com.yidong.jon.retrofitdownload.download;

import com.yidong.jon.retrofitdownload.download.db.DownLoadEntity;

public interface DownLoadTaskListener {
    void onStart();

    void onCancel(DownLoadEntity downLoadEntity);

    void onDownLoading(long downSize);

    void onCompleted(DownLoadEntity downLoadEntity);

    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}
