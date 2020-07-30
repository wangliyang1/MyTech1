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
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

//添加好友
public class AddFriendActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.cont)
    TextView cont;
    @BindView(R.id.ed)
    EditText ed;
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
            String headPic = intent.getStringExtra("head");
            String nam = intent.getStringExtra("name");
            String con = intent.getStringExtra("cont");
            GlideUtils.getCiclePhoto(headPic,head);
            name.setText(nam);
            if (con !=null&&!con.equals("")){
                cont.setText(con);
            }
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_add_friend;
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
                String c = ed.getText().toString().trim();
                HashMap<String,Object> map=new HashMap<>();
                map.put("friendUid",id);
                map.put("remark",c);
                mPresenter.postDoParams(MyUrls.BASE_ADD_FRIEND, CommunityZanBean.class,map);
                break;
        }
    }
}
