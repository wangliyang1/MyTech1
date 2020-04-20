package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.arc.LivenessActivity;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RsaCoder;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
* 账号 18531029738
* 密码 w123456
* */
public class LoginActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.login_weixin)
    ImageView loginWeixin;
    @BindView(R.id.login_face)
    ImageView loginFace;
    String PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof LoginBean && TextUtils.equals("0000", ((LoginBean) o).getStatus())) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.login_register, R.id.login_bt,R.id.login_weixin, R.id.login_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register://进入注册页面
                startActivity(this,RegisterActivity.class);
                break;
            case R.id.login_bt://发送登录请求
                String phone = loginPhone.getText().toString().trim();
                String pwd = loginPwd.getText().toString().trim();
                if (TextUtils.equals("", phone) && TextUtils.equals("", pwd)) {
                    Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                String s = null;
                try {
                    s = RsaCoder.encryptByPublicKey(pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                boolean matches = Pattern.matches(PHONE, phone);
                if (matches){
                    Log.i("xxx", s);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("phone", phone);
                    hashMap.put("pwd",s);
                    mPresenter.postDoParams(MyUrls.LOGIN_URL, LoginBean.class, hashMap);
                }else {
                    Toast.makeText(this, "手机号不符合规范", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.login_weixin:
                LivenessActivity.flag = 1;
                startActivity(new Intent(LoginActivity.this, LivenessActivity.class));
                break;
            case R.id.login_face:
                LivenessActivity.flag = 2;
                startActivity(new Intent(LoginActivity.this, LivenessActivity.class));
                break;
        }
    }




}
