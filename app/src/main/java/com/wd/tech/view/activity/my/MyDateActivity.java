package com.wd.tech.view.activity.my;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.DoTaskBean;
import com.wd.tech.bean.my.FindSingRecordingBean;
import com.wd.tech.bean.my.FindUserSingBean;
import com.wd.tech.mydatepicker.DPCManager;
import com.wd.tech.mydatepicker.DPDecor;
import com.wd.tech.mydatepicker.DatePicker;
import com.wd.tech.mydatepicker.DatePicker2;
import com.wd.tech.presenter.TechPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDateActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.data_bt)
    Button dataBt;
    @BindView(R.id.data_dp)
    DatePicker2 dataDp;
    String result = "";//日期
    private ArrayList<String> result1  = new ArrayList<>();
    @Override
    protected void initData() {
        dataDp.setDate(2020, 5);
        dataDp.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Color.RED);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2F, paint);
            }
        });

        dataDp.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
            @Override
            public void onDateSelected(List<String> date) {
                Iterator iterator = date.iterator();
                while (iterator.hasNext()) {
                    result += iterator.next();
                    if (iterator.hasNext()) {
                        result += "\n";
                    }
                }
                Toast.makeText(MyDateActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {
        //隐藏标题
        getSupportActionBar().hide();
        //当月签到的所有日期
        Intent intent = getIntent();
        if (intent!=null){
            Serializable tmd1 = intent.getSerializableExtra("tmd");
            result1 = (ArrayList<String>) tmd1;
            for (int i = 0; i < result1.size(); i++) {
                String a = "";
                String s = result1.get(i);
                String[] split = s.split("-");
                for (int j = 0; j < split.length; j++) {
                    int i1 = Integer.parseInt(split[j]);
                    String s1 = String.valueOf(i1);
                    a+=s1+"-";
                }
                String substring = a.substring(0, a.length() - 1);
                result1.set(i,substring);
            }
            DPCManager.getInstance().setDecorBG(result1);
        }

        //查询当天签到的状态
        mPresenter.getNoParams(MyUrls.FIND_USER_SIGN, FindUserSingBean.class);

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_my_date;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof DoTaskBean){
            if(TextUtils.equals("0000",((DoTaskBean) o).getStatus())){
                Toast.makeText(this, ""+((DoTaskBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                HashMap<String, Object> map = new HashMap<>();
                map.put("taskId",1001);
                mPresenter.postDoParams(MyUrls.DO_TASK, DoTaskBean.class,map);
            }else {
                Toast.makeText(this, ""+((DoTaskBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (o instanceof FindUserSingBean && TextUtils.equals("0000",((FindUserSingBean) o).getStatus())){
            if (((FindUserSingBean) o).getResult()==1){
                dataBt.setText("已签到");
            }else {
                dataBt.setText("未签到");
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }



    @OnClick({R.id.back, R.id.data_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.data_bt:
                dataBt.setText("已签到");
                mPresenter.postNoParams(MyUrls.USER_SIGN, DoTaskBean.class);
                break;
        }
    }
}
