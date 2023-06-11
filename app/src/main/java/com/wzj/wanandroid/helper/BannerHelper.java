package com.wzj.wanandroid.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.wzj.wanandroid.bean.Banner;
import com.wzj.wanandroid.bean.BannerBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 进入程序 查数据库是否有数据  banner.getData()==null
 * 有数据 直接加载
 * 没有数据 访问网络获取数据 然后存储到数据库中
 */
public class BannerHelper {


    private static final String TAG = "BannerHelper";

    /**
     * 保存所有数据
     */
    public static boolean saveBanner(List<Banner.DataBean> list) {
        //删除原有数据
        LitePal.deleteAll(BannerBean.class);
        boolean result = false;
        //存储数据
        for (Banner.DataBean dataBean : list) {
            BannerBean bannerBean = new BannerBean();
            bannerBean.setId(dataBean.getId());
            bannerBean.setImagePath(dataBean.getImagePath());
            bannerBean.setDesc(dataBean.getDesc());
            bannerBean.setOrder(dataBean.getOrder());
            bannerBean.setTitle(dataBean.getTitle());
            bannerBean.setIsVisible(dataBean.getIsVisible());
            bannerBean.setType(dataBean.getType());
            bannerBean.setUrl(dataBean.getUrl());
            bannerBean.save();
            if(bannerBean.save()){
                result=true;
                Log.d(TAG, "保存BannerBean数据成功");
            }else {
                result=false;
                Log.d(TAG, "保存BannerBean数据失败");
            }
        }
        Log.d(TAG, "保存的数据:"+new Gson().toJson(LitePal.findAll(BannerBean.class)));
        return result;
    }

    /**
     * 查询Banner表中所有数据
     */
    public static List<Banner.DataBean> queryAllBanner() {
        //用于存放查询出来的数据
        List<Banner.DataBean> result = new ArrayList<>();
        //从数据库中查询出来的数据
        List<BannerBean> DbBanners = LitePal.findAll(BannerBean.class);
        Log.d(TAG, String.valueOf(DbBanners.size()));
        //判断库中的数据是否为空
        if (DbBanners != null && DbBanners.size() > 0) {
            //不为空 创建一个新的集合
            for (int i = 0; i < DbBanners.size(); i++) {
                Banner.DataBean dataBean = new Banner.DataBean();
                dataBean.setId(DbBanners.get(i).getId());
                dataBean.setImagePath(DbBanners.get(i).getImagePath());
                dataBean.setDesc(DbBanners.get(i).getDesc());
                dataBean.setOrder(DbBanners.get(i).getOrder());
                dataBean.setTitle(DbBanners.get(i).getTitle());
                dataBean.setIsVisible(DbBanners.get(i).getIsVisible());
                dataBean.setType(DbBanners.get(i).getType());
                dataBean.setUrl(DbBanners.get(i).getUrl());
                result.add(dataBean);
            }
        }
        return result;

    }
}
