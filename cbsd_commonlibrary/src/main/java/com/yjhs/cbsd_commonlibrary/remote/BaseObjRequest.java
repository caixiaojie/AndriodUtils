package com.yjhs.cbsd_commonlibrary.remote;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;
import com.yjhs.cbsd_commonlibrary.R;

import java.io.IOException;

public abstract class BaseObjRequest<T,K> {


	//实际请求
	protected abstract ResultVO<K> Query_Process() throws LoginException,NoDataException,MsgException,Exception;

	private ResultObjInterface<K> listener;
	protected T requestData;
	protected Gson gson;
	protected Context activeContext;
	private Thread th;

	private boolean cancel;

	private RequestHandler requestHandler=new RequestHandler(){
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			boolean cando=false;
			if(activeContext instanceof Activity){
				Activity m=(Activity)activeContext;
				if(m.isDestroyed()==false) {
					cando = true;
				}
			}

			if (cando==true&&cancel == false && listener != null) {
				switch (msg.what) {
					case 0:
						ResultVO<K> obj = (ResultVO<K>) msg.obj;
						if (obj.success()) {
							listener.success(obj);
						}else if(obj.needLogin()){
							listener.needLogin();
						}else if (obj.noData()) {
							listener.error(obj.getCode(),obj.getMsg());
						}else {
							listener.error(obj.getCode(), obj.getMsg());
						}
						break;
					case 100:
						listener.needLogin();
						break;
					case -8:
						listener.error(-8,activeContext.getString(R.string.no_data));
						break;
					case 2:
						listener.error(-5, msg.obj.toString());
						break;
				}
			}
		}
	};
 
	
	public BaseObjRequest(Context activeContext, T requestData, ResultObjInterface<K> listener) {
		this.activeContext = activeContext;
		this.requestData = requestData;
		this.listener = listener;
		this.gson=new Gson();
	}

	public void send() {
		cancel=false;
		if (RequestHandler.checkConnection(activeContext) == false) {
			Message msg = requestHandler.obtainMessage();
			msg.what = 2;
			msg.obj ="当前网络不可用";
			msg.sendToTarget();
			return;
		}

		th = new Thread() {
			@Override
			public void run() {
				Message msg = requestHandler.obtainMessage();
				try {
					ResultVO<K> result = Query_Process();
					if (result != null) {
						msg.what = 0;
						msg.obj = result;
					} else {
						throw new MsgException("查询失败");
					}
				}catch (NoDataException e){
					msg.what = -8;
					e.printStackTrace();
				}catch (LoginException e) {
					msg.what = 100;
					e.printStackTrace();
				} catch (MsgException e) {
					msg.what = 2;
					msg.obj = e.getMessage();
					e.printStackTrace();
				}catch (IOException e){
					msg.what = 2;
					msg.obj = "服务器失联了";
					e.printStackTrace();
				} catch (Exception e) {
					msg.what = 2;
//					msg.obj = "系统异常";
					msg.obj = e.getMessage();
					e.printStackTrace();
				}
				msg.sendToTarget();
			}
		};
		th.start();

	}
	
	
}
