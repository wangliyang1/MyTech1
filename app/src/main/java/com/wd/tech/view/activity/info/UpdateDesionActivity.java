package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.presenter.TechPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

//修改群简介
public class UpdateDesionActivity extends BaseActivity <TechPresenter>{

    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.content)
    EditText content;
    private int id;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_update_desion;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CommunityZanBean && TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(this, ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick(R.id.post)
    public void onViewClicked() {
        String trim = content.getText().toString().trim();
        if (TextUtils.isEmpty(trim)){
            Toast.makeText(this, "介绍不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("groupId",id);
        map.put("description",trim);
        mPresenter.putDoParams(MyUrls.BASE_UPDATE_JIANJIE, CommunityZanBean.class,map);
    }
}
