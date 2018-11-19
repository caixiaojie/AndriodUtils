package com.yjhs.cbsd_commonlibrary.remote;

import android.content.Context;
import android.util.Log;

import com.yjhs.cbsd_commonlibrary.BuildConfig;
import com.yjhs.cbsd_commonlibrary.utils.Tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class HttpUtils {

    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    public static String postBody(Context context, String uri, Object obj) throws IOException,Exception {

        try {
            String result = null;
            Request request = CommonRequest.createPostRequest(context,uri,new RequestParams(Tools.objectToMap(obj)));
            Response response = mOkHttpClient.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }

    public static String postJson(String uri, String json ) throws IOException,Exception {

        try {
            String result = null;
            RequestBody requestBody = RequestBody.create(JSON,json);
            Request request = new Request.Builder().url(uri).post(requestBody).build();
            Response response = mOkHttpClient.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }

    public static String putJson(String uri, String json ) throws IOException,Exception {

        try {
            String result = null;
            RequestBody requestBody = RequestBody.create(JSON,json);
            Request request = new Request.Builder().url(uri).put(requestBody).build();
            Response response = mOkHttpClient.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }

    public static String deleteJson(String uri, String json ) throws IOException,Exception {

        try {
            String result = null;
            RequestBody requestBody = RequestBody.create(JSON,json);
            Request request = new Request.Builder().url(uri).delete(requestBody).build();
            Response response = mOkHttpClient.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }

    public static String postFile(String uri, Map<String,String> params , FormFile formFile) throws IOException,Exception {

        try {
            String result = null;
            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (formFile != null) {
                // MediaType.parse() 里面是上传的文件类型。
                RequestBody body = RequestBody.create(MediaType.parse("image/*"), formFile.getFile());
                // 参数分别为， 请求key ，文件名称 ， RequestBody
                requestBody.addFormDataPart("file", formFile.getFilename(), body);
            }
            if (params != null && params.size() > 0) {
                // map 里面是请求中所需要的 key 和 value
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Log.d("HttpUtils", "key=="+key+"value=="+value);
                    //key 和 value 都不为空才提交数据
                    if (key != null && value != null) {
                        requestBody.addFormDataPart(key, value);
                    }
                }
            }

            Request request = new Request.Builder().url(uri).post(requestBody.build()).build();
            Response response = mOkHttpClient.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }

    public static String postFiles(String uri, Map<String,String> params , FormFile [] formFiles) throws IOException,Exception {

        try {
            String result = null;
            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (formFiles != null && formFiles.length >0) {
                for (FormFile file : formFiles) {
                    if (file == null) {
                        continue;
                    }
                    // MediaType.parse() 里面是上传的文件类型。
                    RequestBody body = RequestBody.create(MediaType.parse("image/*"), file.getFile());
                    // 参数分别为， 请求key ，文件名称 ， RequestBody
                    requestBody.addFormDataPart("file", file.getFilename(), body);
                }

            }
            if (params != null && params.size() > 0) {
                // map 里面是请求中所需要的 key 和 value
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Log.d("HttpUtils", "key=="+key+"value=="+value);
                    //key 和 value 都不为空才提交数据
                    if (key != null && value != null) {
                        requestBody.addFormDataPart(key, value);
                    }
                }
            }
            Request request = new Request.Builder().url(uri).post(requestBody.build()).build();
            Response response = mOkHttpClient.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }




    public static String post(String uri, Map<String,String> body ) throws IOException,Exception {

        try {
            String result = null;

            OkHttpClient _client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();



            Request.Builder builder=new Request.Builder().url(uri);
            //请求头
            Map<String,String> head = new HashMap<>();
            if(head!=null&&head.size()>0){
                for (Map.Entry<String, String> entry : head.entrySet()) {
                    builder.addHeader(entry.getKey(),entry.getValue());
                }
            }
            FormBody.Builder formBody = new FormBody.Builder();
            //请求体
            if(body!=null&&body.size()>0){
                for (Map.Entry<String, String> entry : body.entrySet()) {
                    formBody.add(entry.getKey(),entry.getValue());
                }
                if (BuildConfig.DEBUG) {
                    Log.e("gaj",formBody.toString());
                }
            }
            RequestBody requestBody = formBody.build();

            Request request = builder.post(requestBody).build();

            Response response = _client.newCall(request).execute();

            int rcode=response.code();
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }
    public static String get(String uri) throws IOException,Exception {

        try {
            String result = null;
            OkHttpClient _client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();


            Request.Builder builder=new Request.Builder().url(uri);


            Request request = builder.get().build();



            Response response = _client.newCall(request).execute();

            int rcode=response.code();
            if (BuildConfig.DEBUG) {
                Log.e("gaj",uri);
            }
            Log.i("rcode","url="+uri+"  rcode="+rcode);
            if (response.isSuccessful()){
                result = response.body().string();
//                result = response.body().toString();

            } else {
                Log.i("result","response.code="+response.code()+"");
                throw new IOException();
            }
            if (BuildConfig.DEBUG) {
                Log.i("result", "" + result);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("服务器连接失败或超时", e);
        }
    }





    private static final int TIME_OUT = 30; //超时参数
    private static OkHttpClient mOkHttpClient;

    //为我们的Client配置参数，使用静态语句块来配置
    static {
        //创建我们Client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                //为构建者填充超时时间
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                //允许重定向
                .followRedirects(true)
                //添加https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okHttpBuilder.build();
    }
}