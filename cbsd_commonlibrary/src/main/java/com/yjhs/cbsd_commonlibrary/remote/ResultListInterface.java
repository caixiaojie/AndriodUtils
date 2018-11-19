package com.yjhs.cbsd_commonlibrary.remote;



public interface ResultListInterface<T> {
	 void success(ResultTVO<T> data);
	void error(int code, String errmsg);

	void needLogin();

}
