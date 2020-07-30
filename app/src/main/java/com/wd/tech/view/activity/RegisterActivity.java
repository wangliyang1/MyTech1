package com.wd.tech.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyApp;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.bean.RegisterBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RsaCoder;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class RegisterActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.register_bt)
    Button registerBt;

    private String name;
    private String phone;
    String PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof RegisterBean && TextUtils.equals("0000", ((RegisterBean) o).getStatus())) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            //注册极光
            JMessageClient.register(phone, MyApp.s1, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    switch (i) {
                        case 0:
                            Toast.makeText(RegisterActivity.this, "极光注册成功", Toast.LENGTH_SHORT).show();
                            break;
                        case 898001:
                            Toast.makeText(RegisterActivity.this, "极光用户名已存在", Toast.LENGTH_SHORT).show();
                            break;
                        case 871301:
                            Toast.makeText(RegisterActivity.this, "极光密码格式错误", Toast.LENGTH_SHORT).show();
                            break;
                        case 871304:
                            Toast.makeText(RegisterActivity.this, "极光密码错误", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            startActivity(this,LoginActivity.class);
            finish();
        } else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.register_bt)
    public void onViewClicked() {
        name = registerName.getText().toString().trim();
        phone = registerPhone.getText().toString().trim();
        String pwd = registerPwd.getText().toString().trim();
        String s = null;
        try {
            s = RsaCoder.encryptByPublicKey(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean matches = Pattern.matches(PHONE, phone);
        if (matches){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("phone", phone);
            hashMap.put("nickName", name);
            hashMap.put("pwd",s);
            mPresenter.postDoParams(MyUrls.REGISTER_URL, RegisterBean.class, hashMap);
        }else {
            Toast.makeText(this, "手机号不符合规范", Toast.LENGTH_SHORT).show();
        }
    }
}
