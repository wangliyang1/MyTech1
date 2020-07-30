package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.my.DoTaskBean;
import com.wd.tech.presenter.TechPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.web_menu)
    ImageView webMenu;
    @BindView(R.id.web_name)
    TextView webName;
    @BindView(R.id.web_web)
    WebView webWeb;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent!=null){
            String content = intent.getStringExtra("content");
            String url = intent.getStringExtra("url");
            webName.setText(content);
            webWeb.loadUrl(url);
            webWeb.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });

        }
        webWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("taskId",1005);
                    mPresenter.postDoParams(MyUrls.DO_TASK, DoTaskBean.class,map);
                } else {
                    // 加载中
                }

            }
        });
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
        return R.layout.activity_web;
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


    @OnClick(R.id.web_menu)
    public void onViewClicked() {
        finish();
    }
}
