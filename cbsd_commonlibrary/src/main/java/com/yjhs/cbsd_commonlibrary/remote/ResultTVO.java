package com.yjhs.cbsd_commonlibrary.remote;

import java.util.ArrayList;

/**
 * @Description 通用返回类（数组�?
 * @Company 重庆伏守科技有限公司
 * @Author zhengwei
 * @Date 2015-1-23
 */
public class ResultTVO<T> {

	private int code;
	private String msg;
	private ArrayList<T> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg == null ? "系统出错" : msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<T> getData() {
		return data;
	}


	public void setData(ArrayList<T> data) {
		this.data = data;
	}


	/**
	 * 操作成功
	 *
	 * @return
	 */
	public boolean success() {
		return code==1;
	}


	/**
	 * 未登录需要登录
	 * @return
	 */
	public boolean needLogin(){
		return code==-1;
	}

	/**
	 * 必填字段未填
	 * @return
	 */
	public boolean shortParameter() {return code==-2;}

	/**
	 * 参数类型错误
	 * @return
	 */
	public boolean parameterTypeError() {return code==-3;}

	/**
	 * 未知错误
	 * @return
	 */
	public boolean unKnownError() {return code==-4;}

	/**
	 * 系统错误
	 * @return
	 */
	public boolean systemError() {return code==-5;}

	/**
	 * access_token错误
	 * @return
	 */
	public boolean access_tokenError() {return code==-6;}

	/**
	 * 参数超长
	 * @return
	 */
	public boolean longParameter() {return code==-7;}

	/**
	 * 暂无数据
	 * @return
	 */
	public boolean noData(){
		return code==-8;
	}
	/**
	 * 签名错误
	 */
	public boolean signError() {
		return code==-10;
	}

	/**
	 * 枚举错误
	 * @return
	 */
	public boolean enumError() {
		return code==-11;
	}

}
