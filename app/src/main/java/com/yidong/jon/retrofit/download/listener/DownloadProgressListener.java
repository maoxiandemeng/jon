package com.yidong.jon.retrofit.download.listener;


/**
 * 成功回调处理
 * Created by jon on 2016/12/26
 */
public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
