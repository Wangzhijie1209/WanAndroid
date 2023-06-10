package com.wzj.wanandroid.application;

import android.app.Application;

import com.llw.mvplibrary.network.INetworkRequiredInfo;
import com.wzj.wanandroid.BuildConfig;

/**
 * 网络访问信息
 */
public class NetworkRequiredInfo implements INetworkRequiredInfo {
    private Application application;

    public NetworkRequiredInfo(Application application) {
        this.application = application;
    }

    /**
     * 版本名
     */
    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 版本号
     */
    @Override
    public String getAppVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);

    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;

    }


    @Override
    public Application getApplicationContext() {
        return application;
    }
}
