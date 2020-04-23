package com.wd.tech.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wd.tech.R;
import com.wd.tech.api.MyApp;
import com.wd.tech.view.consult.ConsultDetailsActivity;

public class MImageGetter implements Html.ImageGetter {
    Context context;
    TextView contents;
    public MImageGetter(Context context, TextView content) {
      this.context = context;
      this.contents = content;
    }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        Glide.with(MyApp.mContext).load(source).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(String.valueOf(resource));
                levelListDrawable.addLevel(1,1,bitmapDrawable);
                levelListDrawable.setBounds(0,0,resource.getIntrinsicWidth(),resource.getIntrinsicHeight());
                levelListDrawable.setLevel(1);
                contents.invalidate();
                contents.setText(contents.getText());
            }
        });
        return levelListDrawable;
    }
}
