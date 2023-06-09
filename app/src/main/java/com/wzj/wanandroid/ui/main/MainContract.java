package com.wzj.wanandroid.ui.main;

import android.annotation.SuppressLint;

import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.network.NetworkApi;
import com.llw.mvplibrary.network.observer.BaseObserver;
import com.wzj.wanandroid.api.ApiService;
import com.wzj.wanandroid.bean.WallPaperResponse;

public class MainContract {

    public static class MainPresenter extends BasePresenter<IMainView> {
        @SuppressLint("CheckResult")
        public void getWallPaper() {
            ApiService service = NetworkApi.createService(ApiService.class, 0);
            service.getWallPaper().compose(NetworkApi.applySchedulers(new BaseObserver<WallPaperResponse>() {
                @Override
                public void onSuccess(WallPaperResponse wallPaperResponse) {
                    if (getView() != null) {
                        getView().getWallPager(wallPaperResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getWallPagerFailed(e);
                    }
                }
            }));
        }
    }

    public interface IMainView extends BaseView {
        void getWallPager(WallPaperResponse wallPaperResponse);

        void getWallPagerFailed(Throwable e);
    }
}
