package com.yidong.jon.utils;

import com.yidong.jon.model.Adv;
import com.yidong.jon.retrofit.ListResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface ApiRequest {
//    String end_point = "http://180.163.192.15:8080/eland/api/";
    String end_point = "http://10.9.174.35:8090/eland/api/";

    @GET("elandAdv/getAdvPosition")
    Call<ListResult<Adv>> getData(@Query("apName") String apName);

    @GET("elandAdv/getAdvPosition")
    Observable<ListResult<Adv>> getObData(@Query("apName") String apName);
}
