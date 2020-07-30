package com.wd.tech.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.wd.tech.bean.my.DeletePostBean;
import com.wd.tech.bean.my.DoTaskBean;
import com.wd.tech.bean.my.FindSingRecordingBean;
import com.wd.tech.bean.my.UserInfoBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.MD5Util;
import com.wd.tech.view.activity.my.GuanZhuActivity;
import com.wd.tech.view.activity.my.IntegralActivity;
import com.wd.tech.view.activity.my.MyDateActivity;
import com.wd.tech.view.activity.my.MyPostActivity;
import com.wd.tech.view.activity.my.SheActivity;
import com.wd.tech.view.activity.my.ShoCangActivity;
import com.wd.tech.view.activity.my.TaskListActivity;
import com.wd.tech.view.activity.my.TongZhiActivity;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.ConsultFragment;
import com.wd.tech.view.fragment.InfoFragment;

import java.io.Serializable;
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
    @BindView(R.id.headPic1)//头像
    ImageView headPic1;
    @BindView(R.id.name)//昵称
    TextView name;
    @BindView(R.id.dersign)//个性签名
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
    @BindView(R.id.sing_image)//签到图片
    ImageView sing_image;
    @BindView(R.id.sign)//签到
    TextView sign;
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
            String task = intent.getStringExtra("task");
            if (TextUtils.equals("123",task)){
                vp.setCurrentItem(2);
            }
            String code = intent.getStringExtra("code");
            Toast.makeText(this, ""+code, Toast.LENGTH_SHORT).show();
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",code);
            mPresenter.postDoParams(MyUrls.BIND_WX, DeletePostBean.class,map);//绑定
            mPresenter.postweixin(MyUrls.LOGIN_WX_URL,"123",code,WXBean.class);//登录

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
            int uid = sp.getInt("uid", -1);
            String sid = sp.getString("sid", "");
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", uid);
            map.put("sessionId", sid);
            mPresenter.doGetHeaderParams(MyUrls.BASE_BYID, UserInfoBean.class, map);
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

    @OnClick({R.id.login_iv, R.id.login,R.id.shou,R.id.guan,R.id.tie,R.id.tong,R.id.ji,R.id.ren,R.id.she,R.id.sing_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv:
                break;
            case R.id.login://进入登录页面
                startActivity(this,LoginActivity.class);
                break;
            case R.id.shou://我的收藏
                startActivity(this, ShoCangActivity.class);
                break;
            case R.id.guan://我的关注
                startActivity(this, GuanZhuActivity.class);
                break;
            case R.id.tie://我的帖子
                startActivity(this, MyPostActivity.class);
                break;
            case R.id.tong://我的通知
                startActivity(this, TongZhiActivity.class);
                break;
            case R.id.ji://我的积分
                startActivity(this, IntegralActivity.class);
                break;
            case R.id.ren://我的任务
                startActivity(this, TaskListActivity.class);
                break;
            case R.id.she://我的设置
                startActivity(this, SheActivity.class);
                break;
            case R.id.sing_image:
                //查询当月签到的日期
                mPresenter.getNoParams(MyUrls.FIND_RECORDING, FindSingRecordingBean.class);
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
        if (o instanceof UserInfoBean && TextUtils.equals("0000", ((UserInfoBean) o).getStatus())) {
            UserInfoBean.ResultBean result = ((UserInfoBean) o).getResult();
            GlideUtils.getCiclePhoto(result.getHeadPic(), headPic1);
            dersign.setText(result.getSignature() + "");
            name.setText(result.getNickName()+"");
        }
        if (o instanceof FindSingRecordingBean && TextUtils.equals("0000",((FindSingRecordingBean) o).getStatus())){
            List<String> result = ((FindSingRecordingBean) o).getResult();
            Intent intent = new Intent(this,MyDateActivity.class);
            intent.putExtra("tmd",(Serializable) result);
            startActivity(intent);
        }
        if(o instanceof DeletePostBean){

        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
