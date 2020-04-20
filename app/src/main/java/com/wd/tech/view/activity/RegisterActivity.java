package com.wd.tech.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.presenter.TechPresenter;

public class RegisterActivity extends BaseActivity<TechPresenter> {


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
        return R.layout.activity_register;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}
