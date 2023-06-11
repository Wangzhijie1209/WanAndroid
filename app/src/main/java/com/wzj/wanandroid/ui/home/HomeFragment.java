package com.wzj.wanandroid.ui.home;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.llw.mvplibrary.mvp.MvpFragment;
import com.wzj.wanandroid.R;
import com.wzj.wanandroid.bean.Banner;
import com.wzj.wanandroid.bean.BannerBean;
import com.wzj.wanandroid.helper.BannerHelper;
import com.wzj.wanandroid.utils.AppStartUpUtils;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;

import org.litepal.LitePal;
import org.litepal.exceptions.DataSupportException;

import java.util.ArrayList;
import java.util.List;

/**
 * 整体流程:
 *  Banner:
 *      判断是否有网络
 *          有网络
 *              判断今天是否是第一次打开
 *                  是
 *                      说明现在没有数据,那就请求接口数据
 *                          请求接口数据 判断 有没有数据
 *                              有  显示Banner 使用BannerHelper保存数据
 *                              没有  提示没有请求到数据
 *                  不是
 *                      说明现在可能是有数据的,从数据库中拿 BannerHelper.queryAllBanner 拿到数据 判断一下有没有数据
 *                          没有   重新请求
 *                              请求接口数据 判断请求回来的数据 有没有数据
 *                                  有显示banner 使用BannerHelper保存数据
 *                                  没有 提示没有请求到数据
 *                          有  直接加载Banner showBanner
 *
 *          无网络 加载默认数据
 *
 */

public class HomeFragment extends MvpFragment<HomeContract.HomePresenter> implements HomeContract.HomeView {

    private static final String TAG = "HomeFragment";
    private com.youth.banner.Banner banner;
    private RecyclerView home_recycler_view;
    private List<BannerBean> bannerList = new ArrayList<>();
    private TextView home_banner_title;

    /**
     * 获取到Banner的数据了
     *
     * @param banner
     */
    @Override
    public void getBannerResponse(Banner banner) {
        List<Banner.DataBean> data = banner.getData();
        if(data.size()>0){//有数据
            //数据显示
            showBanner(data);
            //保存数据
            BannerHelper.saveBanner(data);
        }else {
            showMsg("数据为空");
        }
    }

    @Override
    public void getBannerFailed(Throwable throwable) {
        Log.d("TAG", throwable.toString());
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        banner = (com.youth.banner.Banner) getView().findViewById(R.id.banner);
        home_recycler_view = (RecyclerView) getView().findViewById(R.id.home_recycler_view);
        home_banner_title = (TextView) getView().findViewById(R.id.home_banner_title);

        /**
         * 1.判断是否今天第一次打开 第一次 请求网络
         * 2.不是第一次 读取本地数据库  没有数据 重新请求
         * 3.有数据 显示
         */

        //是否有网络
        if(hasNetwork()){
            if(AppStartUpUtils.isTodayFirstStartApp(context)){//判断是否是今天第一次打开
                //今天第一次打开 请求网络
                mPresenter.getBanner();
            }else {//今天后续启动
                //读取本地数据库数据
                List<Banner.DataBean> dataBeans = BannerHelper.queryAllBanner();
                if(dataBeans.size()<=0){//判断有没有数据,没有 重新请求
                    mPresenter.getBanner();
                }else {
                    //有数据,显示
                    showBanner(dataBeans);
                }
            }
        }else {

        }

    }

    private void showBanner(List<Banner.DataBean> list) {

        //设置数据
        banner.setAdapter(new BannerImageAdapter<Banner.DataBean>(list) {
            @Override
            public void onBindView(BannerImageHolder holder, Banner.DataBean data, int position, int size) {
                Glide.with(holder.imageView).load(data.getImagePath()).into(holder.imageView);

            }

        });
        banner.setIndicator(new CircleIndicator(getActivity()));

        banner.addOnPageChangeListener(new OnPageChangeListener() {
            //在页面滚动时被调用,
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //新的页面被被循环中时调用
            @Override
            public void onPageSelected(int position) {
                home_banner_title.setText(list.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;

    }

    @Override
    protected HomeContract.HomePresenter createPresent() {
        return new HomeContract.HomePresenter();
    }
}