package com.wd.tech.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wd.tech.R;
import com.wd.tech.arc.LivenessActivity;

public class RenLianActivity extends AppCompatActivity {
    private Button renlianzhuce;
    private Button renlianduibi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_lian);

        renlianzhuce = findViewById(R.id.renlianzhuce);
        renlianduibi = findViewById(R.id.renliandenglu);

        renlianzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivenessActivity.flag = 1;
                startActivity(new Intent(RenLianActivity.this, LivenessActivity.class));
            }
        });

        renlianduibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivenessActivity.flag = 2;
                startActivity(new Intent(RenLianActivity.this, LivenessActivity.class));
                finish();
            }
        });
    }
}
