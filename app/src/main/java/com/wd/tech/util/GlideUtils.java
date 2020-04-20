package com.wd.tech.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;

public class GlideUtils {
    //获取矩形图片
    public void getPhoto(String url, ImageView iv){
        Glide.with(iv).load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(iv);
    }
    //获取圆形图片
    public void getCiclePhoto(String url, ImageView iv){
        Glide.with(iv).load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iv);
    }
}
