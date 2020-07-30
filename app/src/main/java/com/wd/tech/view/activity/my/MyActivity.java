package com.wd.tech.view.activity.my;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.DoTaskBean;
import com.wd.tech.bean.my.UserInfoBean;
import com.wd.tech.presenter.TechPresenter;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.my_wan)
    TextView myWan;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_sex)
    TextView mySex;
    @BindView(R.id.my_qianming)
    ImageView myQianming;
    @BindView(R.id.my_shengri)
    TextView myShengri;
    @BindView(R.id.my_email)
    TextView myEmail;
    private SharedPreferences sp;
    private SharedPreferences edit;
    private int my;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", Context.MODE_PRIVATE);//账号信息
        edit = getSharedPreferences("task.dp", MODE_PRIVATE);//任务信息
        my = edit.getInt("my", -1);
        int uid = sp.getInt("uid", -1);
        String sid = sp.getString("sid", "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", uid);
        map.put("sessionId", sid);
        mPresenter.doGetHeaderParams(MyUrls.BASE_BYID, UserInfoBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserInfoBean && TextUtils.equals("0000", ((UserInfoBean) o).getStatus())) {
            UserInfoBean.ResultBean result = ((UserInfoBean) o).getResult();
            //姓名
            myName.setText(result.getNickName());
            //性别
            int sex = result.getSex();
            if (sex == 1) {
                mySex.setText("男");
            } else {
                mySex.setText("女");
            }
            //日期
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat1.format(result.getBirthday());
            myShengri.setText(format);
            //邮箱
            myEmail.setText(result.getEmail()+"");
        }
        if (o instanceof DoTaskBean){
            if (TextUtils.equals("0000",((DoTaskBean) o).getStatus())){
                //完善个人信息成功后 存入sp数据 并关闭当前页面
                Toast.makeText(this, ""+((DoTaskBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                edit.edit().putInt("my",1).commit();
                startActivity(this,TaskListActivity.class);
                finish();
            }else {
                Toast.makeText(this, ""+((DoTaskBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }



    @OnClick({R.id.my_back, R.id.my_wan, R.id.my_name, R.id.my_sex, R.id.my_qianming, R.id.my_shengri, R.id.my_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_back:
                finish();
                break;
            case R.id.my_wan:
                if (my==1){
                    Toast.makeText(this, "个人信息已完善", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("taskId",1006);
                    mPresenter.postDoParams(MyUrls.DO_TASK, DoTaskBean.class,map);
                }
                break;
            case R.id.my_name:
                break;
            case R.id.my_sex:
                break;
            case R.id.my_qianming:
                break;
            case R.id.my_shengri:
                break;
            case R.id.my_email:
                break;
        }
    }
}
