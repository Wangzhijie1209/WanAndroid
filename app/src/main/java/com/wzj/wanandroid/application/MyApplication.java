package com.wzj.wanandroid.application;

import com.llw.mvplibrary.BaseApplication;
import com.llw.mvplibrary.network.NetworkApi;

import org.litepal.LitePal;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new NetworkRequiredInfo(this));

        //数据库初始化
        LitePal.initialize(this);
    }
}
