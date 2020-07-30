package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.info.CreateGroupBean;
import com.wd.tech.presenter.TechPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.CreateGroupCallback;

public class CreateGroupActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.comeback)
    ImageView comeback;
    @BindView(R.id.groupName)
    EditText groupName;
    @BindView(R.id.groupDirsion)
    EditText groupDirsion;
    @BindView(R.id.create)
    Button create;
    private SharedPreferences jp;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        jp = getSharedPreferences("jpgroup", MODE_PRIVATE);
        //群聊名称字数
        InputFilter[] filters = {new InputFilter.LengthFilter(10)};
        groupName.setFilters(filters);
        //群聊描述字数
        InputFilter[] filter = {new InputFilter.LengthFilter(100)};
        groupName.setFilters(filter);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_create_group;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CreateGroupBean && TextUtils.equals("0000",((CreateGroupBean) o).getStatus())){
            Toast.makeText(this, ((CreateGroupBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeback, R.id.create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeback:
                finish();
                break;
            case R.id.create:
                String name = groupName.getText().toString().trim();
                String d = groupDirsion.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(this, "群名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String,Object> map=new HashMap<>();
                map.put("name",name);
                map.put("description",d);
                mPresenter.postDoParams(MyUrls.BASE_CREATE_GROUP, CreateGroupBean.class,map);
                //创建极光群
                JMessageClient.createGroup(name, d, new CreateGroupCallback() {
                    @Override
                    public void gotResult(int i, String s, long l) {
                        SharedPreferences.Editor edit = jp.edit();
                        edit.putString("name",name);
                        edit.putLong("id",l);
                        edit.apply();
                    }
                });
                break;
        }
    }
}
