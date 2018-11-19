package com.yjhs.cbsd_commonlibrary.remote;


import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

public abstract class BaseListRequest<T, K> {

    //实际请求
    protected abstract ResultTVO<K> Query_Process()     throws LoginException,MsgException,NoDataException,Exception;


    private ResultListInterface<K> listener;
    protected T requestData;
    protected Gson gson;
    protected Context activeContext;
    private Thread th;
    private boolean cancel;

    private RequestHandler requestHandler = new RequestHandler() {
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
                        ResultTVO<K> obj = (ResultTVO<K>) msg.obj;
                        if (obj.success()) {
                            listener.success(obj);
                        } else if (obj.needLogin()) {
                            listener.needLogin();
                        }else if (obj.noData()){
                            listener.error(-8, "暂无数据");
                        }else {
                            listener.error(obj.getCode(), obj.getMsg());
                        }
                        break;
                    case 100:
                        listener.needLogin();
                        break;
                    case -8:
                        listener.error(-8, "暂无数据");
                        break;
                    case 2:
                        listener.error(-5, msg.obj.toString());
                        break;
                }
            }
        }
    };

    public BaseListRequest(Context activeContext, T requestData, ResultListInterface<K> listener) {
        this.activeContext = activeContext;
        this.requestData = requestData;
        this.listener = listener;
        this.gson = new Gson();
    }

    public void send() {
        cancel = false;
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
                    ResultTVO<K> result = Query_Process();
                    if (result != null) {
                        msg.what = 0;
                        msg.obj = result;
                    } else {
                        throw new MsgException("查询失败");
                    }
                }catch (NoDataException e) {
                    msg.what = -8;
                }catch (LoginException e) {
                    msg.what = 100;
                } catch (MsgException e) {
                    msg.what = 2;
                    msg.obj = e.getMessage();
                    e.printStackTrace();
                }catch (IOException e){
                    msg.what = 2;
                    msg.obj = "服务器失联了";
                } catch (Exception e) {
                    msg.what = 2;
//                    msg.obj = "系统异常";
                    msg.obj = e.getMessage();;
                    e.printStackTrace();
                }
                msg.sendToTarget();
            }
        };
        th.start();

    }

}
