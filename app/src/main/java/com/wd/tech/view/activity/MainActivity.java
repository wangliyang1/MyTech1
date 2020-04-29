package com.wd.tech.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.IActivity;
import com.wd.tech.base.WXBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.MD5Util;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.ConsultFragment;
import com.wd.tech.view.fragment.InfoFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<TechPresenter> {

    List<Fragment> fglist = new ArrayList<>();
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bgz)
    ImageView bgz;
    @BindView(R.id.headPic1)
    ImageView headPic1;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dersign)
    TextView dersign;
    @BindView(R.id.shou)
    LinearLayout shou;
    @BindView(R.id.guan)
    LinearLayout guan;
    @BindView(R.id.tie)
    LinearLayout tie;
    @BindView(R.id.tong)
    LinearLayout tong;
    @BindView(R.id.ji)
    LinearLayout ji;
    @BindView(R.id.ren)
    LinearLayout ren;
    @BindView(R.id.she)
    LinearLayout she;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.login_iv)
    ImageView loginIv;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.cont)
    LinearLayout cont;
    @BindView(R.id.meun)
    RelativeLayout meun;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private SharedPreferences.Editor edit;


    @Override
    protected void initData() {
        //添加数据
        fglist.add(new ConsultFragment());
        fglist.add(new InfoFragment());
        fglist.add(new CommunityFragment());
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fglist.get(position);
            }

            @Override
            public int getCount() {
                return fglist.size();
            }
        });
        //vp 联动 rg
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
        //默认页面
        rg.check(rg.getChildAt(0).getId());
        vp.setCurrentItem(0);
        Intent intent = getIntent();
        if (intent != null) {
            boolean b = intent.getBooleanExtra("bb",false);
            if (b){
                rg.check(rg.getChildAt(2).getId());
                vp.setCurrentItem(2);
            }
        }
    }

    @Override
    protected void initView() {
        //隐藏标题
        getSupportActionBar().hide();
        edit = getSharedPreferences("login.dp", MODE_PRIVATE).edit();
        //滑动监听
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //主内容随着菜单移动
                int width = drawerView.getWidth();
                cont.setTranslationX(width*slideOffset);
                //初始移动
                double v = width * (1 - 0.618) * (1 - slideOffset);
                meun.setPadding((int)v,0,0,0);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        SharedPreferences sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        boolean b = sp.getBoolean("b", false);
        if (b) {
            ll.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);
            String headPic = sp.getString("headPic", "");
            String nickName = sp.getString("nickName", "");
            Glide.with(this).load(headPic).circleCrop().into(headPic1);
            name.setText(nickName);
        } else {
            ll.setVisibility(View.VISIBLE);
            rl.setVisibility(View.GONE);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void DestroyActivity() {

    }

    @OnClick({R.id.login_iv, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv:
                break;
            case R.id.login:
                startActivity(this,LoginActivity.class);
                break;
        }
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof WXBean && TextUtils.equals("0000",((WXBean) o).getStatus())){
            WXBean.ResultBean result = ((WXBean) o).getResult();
            edit.putBoolean("b",true);
            edit.putString("sid",result.getSessionId());
            edit.putInt("uid",result.getUserId());
            edit.commit();
            Toast.makeText(this, "微信登录成功"+((WXBean) o).getResult().getNickName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
