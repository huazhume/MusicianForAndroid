package com.androidapp.len.doubanmusicsonger.Service;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by len on 16/11/30.
 */

public class DB_DownloadUtils {

    public static void sendRequest(final Context context,
                                   final HttpRequest.HttpMethod method, String url, final RequestParams params,
                                   final DB_DownloadCallback iOAuthCallBack) {

        HttpUtils http = new HttpUtils();

        http.configCurrentHttpCacheExpiry(1000 * 5);
        // 设置超时时间
        http.configTimeout(5 * 1000);
        http.configSoTimeout(5 * 1000);

        if(method== HttpRequest.HttpMethod.GET){

            http.configCurrentHttpCacheExpiry(5000); // 设置缓存5秒,5秒内直接返回上次成功请求的结果。
        }
        http.send(method, url, params, new RequestCallBack<Object>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i("DB_DownloadUtils", "<<<<<<<<< downloadstart");
            }
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.i("DB_DownloadUtils","<<<<<<<<< download bool"+params);
                if (responseInfo.statusCode == 200){
                    Log.i("DB_DownloadUtils","<<<<<<<<< downloadsuccess");
                    //成功
                    //回调
                    Log.i("DB_DownloadUtils","<<<<<<<<< "+responseInfo.result);
                    iOAuthCallBack.callback(responseInfo.result);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("DB_DownloadUtils","<<<<<<<<< downloadfail"+s);
            }
        });
    }
}

