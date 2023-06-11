package com.wzj.wanandroid.api;

import com.wzj.wanandroid.bean.Banner;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ApiService接口 统一管理应用内所有的接口
 */
public interface ApiService {

    @GET("/banner/json")
    Observable<Banner> getBanner();
}
