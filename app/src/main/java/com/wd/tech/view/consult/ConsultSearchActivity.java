package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.ibooker.zflowlayoutlib.FlowLayout;

/*
 * 搜索页面
 * */
public class ConsultSearchActivity extends BaseActivity {

    private String[] strings = {"区块链","中年危机","锤子科技","子弹短信","民营企业","特斯拉","支付包","资本市场","电视剧"};
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_cancel)
    TextView searchCancel;
    @BindView(R.id.search_flowlayout)
    FlowLayout searchFlowlayout;

    @Override
    protected void initData() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < strings.length ; i++) {
            TextView textView = (TextView)inflater.inflate(R.layout.search_tv,searchFlowlayout,false);
            textView.setText(strings[i]);
            searchFlowlayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConsultSearchActivity.this,SearchActivity.class);
                    intent.putExtra("name",textView.getText().toString().trim());
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_consult_search;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick({R.id.search_edit, R.id.search_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_edit:
                startActivity(this,SearchActivity.class);
                break;
            case R.id.search_cancel://取消搜索返回上一页面
                finish();
                break;

        }
    }
}
