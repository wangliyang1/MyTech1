package com.wd.tech.view.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.InfoCollectionBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.InfoCollectionAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 我的收藏
* */
public class ShoCangActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.delete)
    ImageView delete;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 10);
        mPresenter.getDoParams(MyUrls.Base_Info_Collection, InfoCollectionBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_sho_cang;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof InfoCollectionBean && TextUtils.equals("0000", ((InfoCollectionBean) o).getStatus())) {
            List<InfoCollectionBean.ResultBean> result = ((InfoCollectionBean) o).getResult();
            recycler.setLayoutManager(new LinearLayoutManager(this));
            InfoCollectionAdapter infoCollectionAdapter = new InfoCollectionAdapter(result);
            recycler.setAdapter(infoCollectionAdapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.back, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.delete:
                break;
        }
    }
}
