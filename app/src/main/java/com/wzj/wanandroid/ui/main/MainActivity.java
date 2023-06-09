package com.wzj.wanandroid.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.llw.mvplibrary.mvp.MvpActivity;
import com.llw.mvplibrary.network.utils.KLog;
import com.wzj.wanandroid.R;
import com.wzj.wanandroid.bean.WallPaperResponse;
import com.wzj.wanandroid.ui.adapter.WallPaperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author llw
 */
public class MainActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView {

    private static final String TAG = "MainActivity";
    private final List<WallPaperResponse.ResBean.VerticalBean> mList = new ArrayList<>();
    private WallPaperAdapter mAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        //显示加载弹窗
        showLoadingDialog();
        //初始化列表
        initList();
    }

    /**
     * 初始化列表
     */
    private void initList() {
        RecyclerView rv = findViewById(R.id.rv);
        //配置rv
        mAdapter = new WallPaperAdapter(mList, context);
        rv.setLayoutManager(new GridLayoutManager(context, 2));
        rv.setAdapter(mAdapter);

        //请求列表数据
        mPresenter.getWallPaper();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }


    @Override
    public void getWallPager(WallPaperResponse wallPaperResponse) {
        List<WallPaperResponse.ResBean.VerticalBean> vertical = wallPaperResponse.getRes().getVertical();
        if (vertical != null && vertical.size() > 0) {
            mList.clear();
            mList.addAll(vertical);
            mAdapter.notifyDataSetChanged();
        }
        hideLoadingDialog();
    }

    @Override
    public void getWallPagerFailed(Throwable e) {
        KLog.e(TAG,e.toString());
        showMsg("获取列表数据异常，具体日志信息请查看日志");
        hideLoadingDialog();
    }
}
