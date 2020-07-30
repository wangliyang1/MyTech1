package com.wd.tech.api;

import android.app.Application;
import android.content.Context;

import com.example.arclibrary.builder.AcrFaceManagerBuilder;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.arc.Constants;

import cn.jpush.im.android.api.JMessageClient;

import static cn.jpush.im.android.api.JMessageClient.FLAG_NOTIFY_SILENCE;

public class MyApp extends Application {
    public static Context mContext;
    public static IWXAPI mWxApi;
    public static String s1="d4cf77f0d3b85e9edc540dee";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        registerTowX();
        //初始化极光
        JMessageClient.init(this, true);
        JMessageClient.setDebugMode(true);
        //通知管理,通知栏开启，其他关闭
        JMessageClient.setNotificationFlag(FLAG_NOTIFY_SILENCE);
    }

    private void registerTowX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, "wx4c96b6b8da494224", true);
        // 将该app注册到微信
        mWxApi.registerApp("wx4c96b6b8da494224");
    }

    private void initArcFace() {
        new AcrFaceManagerBuilder().setContext(this)
                .setFreeSdkAppId(Constants.FREESDKAPPID)
                .setFdSdkKey(Constants.FDSDKKEY)
                .setFtSdkKey(Constants.FTSDKKEY)
                .setFrSdkKey(Constants.FRSDKKEY)
                .setLivenessAppId(Constants.LIVENESSAPPID)
                .setLivenessSdkKey(Constants.LIVENESSSDKKEY)
                .create();
    }
}
