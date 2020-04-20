package com.wd.tech.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wd.tech.R;

public class IActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void startActivity(Context context,Class cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

}
