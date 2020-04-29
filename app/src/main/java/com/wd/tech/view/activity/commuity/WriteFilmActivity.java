package com.wd.tech.view.activity.commuity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommPlBean;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;
import com.wd.tech.view.adapter.commuity.ComPllistAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 写评论
* */
public class WriteFilmActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeback)
    ImageView comeback;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.et)
    TextView et;
    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.bt)
    Button bt;
    private int id;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        rc.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            String hea = intent.getStringExtra("head");
            String nam = intent.getStringExtra("name");
            GlideUtils.getPhoto(hea,head);
            name.setText(nam);
            HashMap<String, Object> map = new HashMap<>();
            map.put("communityId", id);
            map.put("page",1);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_COMMUNI_PL, CommPlBean.class,map);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_write_film;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        //评论列表
        if (o instanceof CommPlBean&& TextUtils.equals("0000",((CommPlBean) o).getStatus())){
            List<CommPlBean.ResultBean> result = ((CommPlBean) o).getResult();
            rc.setAdapter(new ComPllistAdapter(result));
        }
        //评论成功
        if (o instanceof CommunityZanBean &&TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(this, ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            HashMap<String, Object> map = new HashMap<>();
            map.put("communityId", id);
            map.put("page",1);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_COMMUNI_PL, CommPlBean.class,map);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeback, R.id.bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeback:
                finish();
                break;
            case R.id.bt:
                String str = et.getText().toString().trim();
                if (TextUtils.isEmpty(str)){
                    Toast.makeText(this, "评论为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("communityId",id);
                map.put("content",str);
                mPresenter.postDoParams(MyUrls.BASE_FILM, CommunityZanBean.class,map);
                break;
        }
    }
}
