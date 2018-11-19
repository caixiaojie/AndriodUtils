package com.yjhs.cbsd_commonlibrary.remote;

public class MsgException extends Throwable {
	private String msg;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MsgException(String msg){
		this.msg=msg;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return msg;
	}
	
	
}
