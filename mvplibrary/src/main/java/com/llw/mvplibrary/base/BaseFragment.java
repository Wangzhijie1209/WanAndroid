package com.llw.mvplibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.llw.mvplibrary.R;
import com.llw.mvplibrary.network.utils.NetworkUtils;

import java.util.Objects;

/**
 * 基类Fragment，普通Fragment继承即可。
 * @author llw
 */
public abstract class BaseFragment extends Fragment implements IUiCallback {

    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    private Dialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater;
        if(rootView == null){
            rootView = inflater.inflate(getLayoutId(),null);
        }else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if(viewGroup != null){
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }
    /**
     * 弹窗出现
     */
    protected void showLoadingDialog() {
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
    }
    /**
     * 弹窗隐藏
     */
    protected void hideLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }
    /**
     * 绑定
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.context = (Activity) context;
        }
    }

    /**
     * Toast消息提示  字符
     *
     * @param llw
     */
    protected void showMsg(CharSequence llw) {
        Toast.makeText(context, llw, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast消息提示  资源ID
     *
     * @param resourceId
     */
    protected void showMsg(int resourceId) {
        Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show();
    }


    /**
     * 解绑
     */
    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    /**
     * 检查当前是否打开网络
     */
    protected boolean hasNetwork() {
        return (NetworkUtils.isNetWorkAvailable(context));
    }


}
