package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.consult.RecommendListBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.ConsultAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 频道页面
 * */
public class ChannelActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.consult_menu)
    ImageView consultMenu;
    @BindView(R.id.channel_name)
    TextView channelName;
    @BindView(R.id.consult_search)
    ImageView consultSearch;
    @BindView(R.id.channel_recycler)
    RecyclerView channelRecycler;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("plateId", id);
            map.put("page", 1);
            map.put("count", 5);
            mPresenter.getDoParams(MyUrls.CONSULT_RECOMMEND_LIST, RecommendListBean.class, map);
            channelName.setText(name);
        }
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_channel;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
//列表展示
        if (o instanceof RecommendListBean && TextUtils.equals("0000", ((RecommendListBean) o).getStatus())) {
            List<RecommendListBean.ResultBean> result = ((RecommendListBean) o).getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            ConsultAdapter consultAdapter = new ConsultAdapter(result);

            channelRecycler.setLayoutManager(linearLayoutManager);
            channelRecycler.setAdapter(consultAdapter);

            consultAdapter.setListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(String s) {
                    int i = Integer.parseInt(s);
                    Intent intent = new Intent(ChannelActivity.this, ConsultDetailsActivity.class);
                    intent.putExtra("id", i);
                    startActivity(intent);
                }

                @Override
                public void onLongItemClick(String s) {

                }
            });
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.consult_menu, R.id.consult_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.consult_menu://返回频道页面
                finish();
                break;
            case R.id.consult_search://进入搜索页面
                startActivity(this,ConsultSearchActivity.class);
                break;
        }
    }
}
