package com.wangbo.www.yimiyoupin.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.okhttp.OkHttpClient;
import com.wangbo.www.yimiyoupin.helper.OkHttpClientHelper;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化okhttp
        Fresco.initialize(this);
        initOkHttp();

    }

    private void initOkHttp() {
        //  单列获取okHttpClient对象
        OkHttpClient okHttpClient = OkHttpClientHelper.getOkHttpSingletonInstance();
    }

}
