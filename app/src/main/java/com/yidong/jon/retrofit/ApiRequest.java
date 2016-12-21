package com.yidong.jon.retrofit;

import com.yidong.jon.model.VideoEntity;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface ApiRequest {
    String end_point = "http://newapi.meipai.com/output/";

    @GET("channels_topics_timeline.json")
    Observable<List<VideoEntity>> getVideoList(@QueryMap HashMap<String, Object> map);

    @GET("channels_topics_timeline.json")
    Call<ResponseBody> getVideo(@QueryMap HashMap<String, Object> map);
}
