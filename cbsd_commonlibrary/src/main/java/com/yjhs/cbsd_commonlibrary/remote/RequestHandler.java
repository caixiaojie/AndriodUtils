package com.yjhs.cbsd_commonlibrary.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

/**
 * 功能说明:HTTP请求Handler
 * 作    者:zhengwei
 * 创建日期:2015/11/4 13:03
 * 所属项目:ZQQuan
 */
class RequestHandler extends Handler {

	public static boolean checkConnection(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}