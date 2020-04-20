package com.wd.tech.api;

import android.app.Application;
import android.content.Context;

import com.example.arclibrary.builder.AcrFaceManagerBuilder;
import com.wd.tech.arc.Constants;

public class MyApp extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
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
