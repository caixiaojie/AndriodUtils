package com.yjhs.cbsd_commonlibrary.remote;

import android.content.Context;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * author: Administrator
 * date: 2018/5/14 0014
 * desc:
 */

public class CommonRequest {
    /**
     * 创建Get请求的Request
     *
     * @param url
     * @param params
     * @return 通过传入的参数返回一个Get类型的请求
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }

        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get().build();
    }

    /**
     * 创建Post请求的Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPostRequest(Context context, String url, RequestParams params) {
        FormBody.Builder mFromBodyBuilder = new FormBody.Builder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数逐一遍历添加到我们的请求构建类中
                mFromBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody mFormBody = mFromBodyBuilder.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }

    /**
     * 创建Put请求的Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createPutRequest(String url, RequestParams params) {
        FormBody.Builder mFromBodyBuilder = new FormBody.Builder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数逐一遍历添加到我们的请求构建类中
                mFromBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody mFormBody = mFromBodyBuilder.build();
        return new Request.Builder().url(url).put(mFormBody).build();
    }

    /**
     * 创建Delete请求的Request
     *
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象
     */
    public static Request createDeleteRequest(String url, RequestParams params) {
        FormBody.Builder mFromBodyBuilder = new FormBody.Builder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数逐一遍历添加到我们的请求构建类中
                mFromBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody mFormBody = mFromBodyBuilder.build();
        return new Request.Builder().url(url).delete(mFormBody).build();
    }

    /**
     * 文件上传请求
     *
     * @return
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    /*public static Request createMultiPostRequest(String url, RequestParams params) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {

                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return new Request.Builder()
                .url(url)
                .post(requestBody.build())
                .build();
    }*/

}
