package com.wd.tech.view.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.GuanZhuBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.GuanZhuAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/*
* 我的关注
* */
public class GuanZhuActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 10);
        mPresenter.getDoParams(MyUrls.Base_Find_Follow, GuanZhuBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_guan_zhu;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if(o instanceof GuanZhuBean && TextUtils.equals("0000",((GuanZhuBean) o).getStatus())){
            List<GuanZhuBean.ResultBean> result = ((GuanZhuBean) o).getResult();
            recycler.setLayoutManager(new LinearLayoutManager(this));
            GuanZhuAdapter guanZhuAdapter = new GuanZhuAdapter(result);
            recycler.setAdapter(guanZhuAdapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
