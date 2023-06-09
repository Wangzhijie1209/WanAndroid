package com.wzj.wanandroid.application;

import com.llw.mvplibrary.BaseApplication;
import com.llw.mvplibrary.network.NetworkApi;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new NetworkRequiredInfo(this));
    }
}
