package com.yidong.jon.retrofit.download.listener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by jon on 2016/12/26
 */

public class DownloadInterceptor implements Interceptor {
    private DownloadProgressListener progressListener;

    public DownloadInterceptor(DownloadProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originResponse = chain.proceed(chain.request());

        return originResponse.newBuilder()
                .body(new DownloadResponseBody(originResponse.body(), progressListener))
                .build();
    }
}
