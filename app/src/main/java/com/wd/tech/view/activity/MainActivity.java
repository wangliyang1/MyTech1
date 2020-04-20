package com.wd.tech.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wd.tech.R;
import com.wd.tech.base.IActivity;
import com.wd.tech.util.RsaCoder;

public  class MainActivity extends IActivity {

    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String a = "a123456";
        try {
            s = RsaCoder.encryptByPublicKey(a);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("xxx",s);
    }
}
