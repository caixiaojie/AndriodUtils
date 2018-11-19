package com.yjhs.andriodutils;

import android.content.Context;

/**
 * author: Administrator
 * date: 2018/10/16 0016
 * desc:
 */
class SizeUtils {
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
