package com.wzj.wanandroid.api;

import com.wzj.wanandroid.bean.WallPaperResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ApiService接口 统一管理应用内所有的接口
 */
public interface ApiService {
    /**
     * 获取热门壁纸列表
     */
    @GET("/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    Observable<WallPaperResponse> getWallPaper();
}
