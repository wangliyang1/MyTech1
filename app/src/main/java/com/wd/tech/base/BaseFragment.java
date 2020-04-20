package com.wd.tech.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wd.tech.contract.IContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements IContract.IView {
    protected P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view=View.inflate(getContext(),layoutId(),null);
        mPresenter=providePresenter();
        //绑定
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        //黄油刀
        bind = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    protected abstract void initView(View view);

    protected abstract P providePresenter();

    protected abstract int layoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (bind != null) {
            bind.unbind();
        }
        DestroyFragment();
    }

    protected abstract void DestroyFragment();
}
