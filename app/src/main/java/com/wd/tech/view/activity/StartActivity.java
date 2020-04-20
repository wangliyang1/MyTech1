package com.wd.tech.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.base.IActivity;

public class StartActivity extends IActivity {

    private Handler handler = new Handler(){
        int time = 1;

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (time == 3) {
                    Intent intent = new Intent(StartActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    time++;
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
