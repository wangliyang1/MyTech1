package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.bean.info.FriendInfoByIdBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import butterknife.BindView;
//邀请好友进群
public class InviteActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.sou)
    ImageView sou;
    @BindView(R.id.query)
    EditText query;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bt)
    Button bt;
    private int id;
    private int userId;
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
        ll.setVisibility(View.GONE);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        //查询好友
        if (o instanceof FriendInfoByIdBean && TextUtils.equals("0000",((FriendInfoByIdBean) o).getStatus())){
            ll.setVisibility(View.VISIBLE);
            FriendInfoByIdBean.ResultBean result = ((FriendInfoByIdBean) o).getResult();
            String headPic = result.getHeadPic();
            String nickName = result.getNickName();
            name.setText(nickName);
            GlideUtils.getCiclePhoto(headPic,head);
            userId = result.getUserId();
        }
        //邀请好友
        if (o instanceof CommunityZanBean &&TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(this, ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
