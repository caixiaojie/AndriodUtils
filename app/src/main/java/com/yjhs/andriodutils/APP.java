package com.yjhs.andriodutils;

import android.app.Application;
import android.content.Context;

import com.yjhs.cbsd_commonlibrary.utils.AppUtils;


/**
 * author: Administrator
 * date: 2018/10/30 0030
 * desc:
 */
public class APP extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        AppUtils.init(mContext);
    }
}
