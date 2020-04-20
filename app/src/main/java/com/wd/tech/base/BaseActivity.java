package com.wd.tech.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wd.tech.contract.IContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity <P extends BasePresenter>extends IActivity implements IContract.IView {
    protected P mPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(layoutId());
        mPresenter = providePresenter();
        if (mPresenter!=null){
            //绑定
            mPresenter.attach(this);
        }
        //黄油刀
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providePresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (bind != null) {
            bind.unbind();
        }
        DestroyActivity();
    }

    protected abstract void DestroyActivity();
}
