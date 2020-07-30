package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

//申请加群
public class AddGroupActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.et)
    EditText et;
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
        return R.layout.activity_add_group;
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
    @OnClick({R.id.comeBack, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack:
                finish();
                break;
            case R.id.send:
                String trim = et.getText().toString().trim();
                HashMap<String,Object> map=new HashMap<>();
                map.put("groupId",id);
                map.put("remark",trim);
                mPresenter.postDoParams(MyUrls.BASE_ADD_GROUP, CommunityZanBean.class,map);
                break;
        }
    }
}
