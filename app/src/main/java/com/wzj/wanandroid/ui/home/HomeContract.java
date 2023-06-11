package com.wzj.wanandroid.ui.home;

import android.annotation.SuppressLint;

import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.network.NetworkApi;
import com.llw.mvplibrary.network.observer.BaseObserver;
import com.wzj.wanandroid.api.ApiService;
import com.wzj.wanandroid.bean.Banner;

/**
 * 首页契约类
 */
public class HomeContract {

    public static class HomePresenter extends BasePresenter<HomeView> {

        /**
         * 获取首页Banner图片
         */
        @SuppressLint("CheckResult")
        public void getBanner() {
            ApiService service = NetworkApi.createService(ApiService.class, 0);
            service.getBanner().compose(NetworkApi.applySchedulers(new BaseObserver<Banner>() {
                @Override
                public void onSuccess(Banner banner) {
                    if (getView() != null) {
                        getView().getBannerResponse(banner);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getBannerFailed(e);
                    }
                }
            }));
        }
    }

    public interface HomeView extends BaseView {
        /**
         * 获取Banner成功
         *
         * @param banner
         */
        void getBannerResponse(Banner banner);

        void getBannerFailed(Throwable throwable);
    }
}
