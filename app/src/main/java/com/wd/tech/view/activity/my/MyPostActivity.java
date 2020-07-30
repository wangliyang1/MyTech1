package com.wd.tech.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.DeletePostBean;
import com.wd.tech.bean.my.MyPostBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.MyPostAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPostActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.my_post_back)//返回上一页面
    ImageView myPostBack;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.my_post_recycler)
    RecyclerView myPostRecycler;
    @BindView(R.id.my_post_ok)
    TextView myPostOk;
    @BindView(R.id.my_post_no)
    TextView myPostNo;
    @BindView(R.id.my_post_del)
    LinearLayout myPostDel;
    private HashMap<String, Object> hashMap;
    private MyPostAdapter myPostAdapter;
    private HashMap<String, Object> map;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        map = new HashMap<>();
        map.put("page",1);
        map.put("count",5);
        //开始请求数据
        mPresenter.getDoParams(MyUrls.MY_Post, MyPostBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_my_post;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof MyPostBean && TextUtils.equals("0000",((MyPostBean) o).getStatus())){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            List<MyPostBean.ResultBean> newresult = ((MyPostBean) o).getResult();
            myPostAdapter = new MyPostAdapter(newresult);

            myPostAdapter.setListener(new OnRecyclerItemClickListener() {

                @Override
                public void onItemClick(String s) {
                    hashMap = new HashMap<>();
                    hashMap.put("communityId",s);
                    myPostDel.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLongItemClick(String s) {

                }
            });

            myPostRecycler.setLayoutManager(linearLayoutManager);
            myPostRecycler.setAdapter(myPostAdapter);
        }
        if (o instanceof DeletePostBean ){
            if(TextUtils.equals("0000",((DeletePostBean) o).getStatus())){
                Toast.makeText(this, ""+((DeletePostBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                mPresenter.getDoParams(MyUrls.MY_Post, MyPostBean.class,map);
                myPostDel.setVisibility(View.GONE);
            }else {
                Toast.makeText(this, ""+((DeletePostBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                myPostDel.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.my_post_back, R.id.my_post_ok, R.id.my_post_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_post_back://返回上一页面
                finish();
                break;
            case R.id.my_post_ok://确定删除
                mPresenter.dltDoParams(MyUrls.DELETE_POST, DeletePostBean.class,hashMap);
                break;
            case R.id.my_post_no://取消
                myPostDel.setVisibility(View.GONE);
                break;
        }
    }
}
