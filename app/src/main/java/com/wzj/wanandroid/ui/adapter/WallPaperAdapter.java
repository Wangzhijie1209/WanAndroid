package com.wzj.wanandroid.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wzj.wanandroid.R;
import com.wzj.wanandroid.bean.WallPaperResponse;

import java.util.List;

public class WallPaperAdapter extends BaseQuickAdapter<WallPaperResponse.ResBean.VerticalBean, BaseViewHolder> {

    Context context;
    public WallPaperAdapter(List<WallPaperResponse.ResBean.VerticalBean> data, Context context) {
        super(R.layout.item_wallpaper, data);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WallPaperResponse.ResBean.VerticalBean item) {
        ImageView imageView = helper.getView(R.id.image);
        Glide.with(context).load(item.getImg()).into(imageView);
    }
}

