package com.wzj.wanandroid.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppStartUpUtils {

    /**
     * 判断是否是首次启动
     */
    public static boolean isFirstStartApp(Context context) {
        boolean isFirst = SPUtils.getBoolean(Constant.START_UP_APP_TIME, true, context);
        //第一次
        if (isFirst) {
            SPUtils.putBoolean(Constant.APP_FIRST_START, false, context);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是今日首次启动APP
     */
    public static boolean isTodayFirstStartApp(Context context) {
        String saveDate= SPUtils.getString(Constant.START_UP_APP_TIME, "2020-08-27", context);
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //第一次打开
        if(!saveDate.equals(todayDate)){//时间不一致 说明是第一次打开 返回true
            SPUtils.putString(Constant.START_UP_APP_TIME,todayDate,context);
            return true;
        }else {
            return false;
        }
    }

}
