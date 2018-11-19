package com.yjhs.cbsd_commonlibrary.remote;

/**
 * 功能说明:请求方法（单个对象）处理接口
 * 作    者:zhengwei
 * 创建日期:2015/11/4 13:03
 * 所属项目:通用1.0
 */
public interface ResultObjInterface<T> {
	void success(ResultVO<T> data);
	void error(int code, String errmsg);
	void needLogin();

}
