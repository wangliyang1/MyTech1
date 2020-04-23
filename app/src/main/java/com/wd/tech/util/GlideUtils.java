package com.wd.tech.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.api.MyApp;

public class GlideUtils {
    //获取矩形图片
    public static void getPhoto(String url, ImageView iv){
        Glide.with(MyApp.mContext).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(iv);
    }
    //获取圆形图片
    public static void getCiclePhoto(String url, ImageView iv){
        Glide.with(MyApp.mContext).load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iv);
    }
}
