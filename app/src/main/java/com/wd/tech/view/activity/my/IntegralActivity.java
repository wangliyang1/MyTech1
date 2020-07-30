package com.wd.tech.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.FindUserSingBean;
import com.wd.tech.bean.my.IntegralRecordBean;
import com.wd.tech.bean.my.UserIntegralBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.IntegralAdapter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * 积分页
 * */
public class IntegralActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.my_amount)
    TextView myAmount;
    @BindView(R.id.my_result)
    TextView myResult;
    @BindView(R.id.my_recycler)
    RecyclerView myRecycler;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        mPresenter.getNoParams(MyUrls.USER_INTEGRAL, UserIntegralBean.class);//查询用户积分
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",1);
        map.put("count",8);
        mPresenter.getDoParams(MyUrls.USER_RECORD, IntegralRecordBean.class,map);//查询用户积分明细
        mPresenter.getNoParams(MyUrls.SIGN_DAY, FindUserSingBean.class);//查询用户连续签到天数
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserIntegralBean && TextUtils.equals("0000",((UserIntegralBean) o).getStatus())){
            myAmount.setText(((UserIntegralBean) o).getResult().getAmount()+"");
        }
        if (o instanceof IntegralRecordBean && TextUtils.equals("0000",((IntegralRecordBean) o).getStatus())){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            IntegralAdapter integralAdapter = new IntegralAdapter(((IntegralRecordBean) o).getResult());

            myRecycler.setAdapter(integralAdapter);
            myRecycler.setLayoutManager(linearLayoutManager);
        }
        if (o instanceof FindUserSingBean && TextUtils.equals("0000",((FindUserSingBean) o).getStatus())){
            myResult.setText("您已连续签到"+((FindUserSingBean) o).getResult()+"天");
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

}
