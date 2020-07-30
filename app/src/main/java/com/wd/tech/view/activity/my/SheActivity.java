package com.wd.tech.view.activity.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.tech.R;
import com.wd.tech.api.MyApp;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.BingWXBean;
import com.wd.tech.bean.my.DoTaskBean;
import com.wd.tech.bean.my.UserInfoBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;
import com.wd.tech.view.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class SheActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.she_login)//退出登录
    TextView sheLogin;
    @BindView(R.id.user_headprice)
    ImageView userHeadprice;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.user_qianming)
    ImageView userQianming;
    @BindView(R.id.user_britary)
    TextView userBritary;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_youxiang)
    TextView userYouxiang;
    @BindView(R.id.user_jifen)
    TextView userJifen;
    @BindView(R.id.user_vip)
    TextView userVip;
    @BindView(R.id.user_facebind)
    TextView userFacebind;
    @BindView(R.id.user_updatepwd)
    TextView userUpdatepwd;
    @BindView(R.id.user_weixinid)
    TextView userWeixinid;
    private SharedPreferences sp;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //隐藏标题栏
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", Context.MODE_PRIVATE);
        int uid = sp.getInt("uid", -1);
        String sid = sp.getString("sid", "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", uid);
        map.put("sessionId", sid);
        mPresenter.doGetHeaderParams(MyUrls.BASE_BYID, UserInfoBean.class, map);
        //判断是否绑定微信
        mPresenter.getNoParams(MyUrls.BING_WX_CHAT, BingWXBean.class);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_she;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserInfoBean && TextUtils.equals("0000", ((UserInfoBean) o).getStatus())) {
            UserInfoBean.ResultBean result = ((UserInfoBean) o).getResult();
            GlideUtils.getCiclePhoto(result.getHeadPic(), userHeadprice);
            //日期
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat1.format(result.getBirthday());
            userBritary.setText(format);
            userFacebind.setText(result.getWhetherFaceId() + "");
            userJifen.setText(result.getIntegral() + "");
            userName.setText(result.getNickName() + "");
            int sex = result.getSex();
            if (sex == 1) {
                userSex.setText("男");
            } else {
                userSex.setText("女");
            }
            int whetherFaceId = result.getWhetherFaceId();
            if (whetherFaceId == 1) {
                userFacebind.setText("已绑定");
            } else {
                userFacebind.setText("未绑定");
            }
            int whetherVip = result.getWhetherVip();
            if (whetherVip == 1) {
                userVip.setText("是");
            } else {
                userVip.setText("否");
            }

            userPhone.setText(result.getPhone() + "");
            userYouxiang.setText(result.getEmail() + "");
        }
        if (o instanceof BingWXBean && TextUtils.equals("0000",((BingWXBean) o).getStatus())){
            if (((BingWXBean) o).getBindStatus()==1){
                userWeixinid.setText("已绑定");
                HashMap<String, Object> map = new HashMap<>();
                map.put("taskId",1007);
                mPresenter.postDoParams(MyUrls.DO_TASK, DoTaskBean.class,map);
            }else {
                userWeixinid.setText("未绑定");
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.user_qianming, R.id.user_facebind, R.id.she_login,R.id.user_updatepwd,R.id.user_weixinid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_qianming:
                break;
            case R.id.user_facebind:
                break;
            case R.id.she_login:
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("b", false);
                editor.commit();
                Intent intent = new Intent(SheActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.user_updatepwd:
                break;
            case R.id.user_weixinid:
                String trim = userWeixinid.getText().toString().trim();
                if (TextUtils.equals("未绑定",trim)){
                    if (!MyApp.mWxApi.isWXAppInstalled()){
                        Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope="snsapi_userinfo";
                    req.state="diandi_wx_login";
                    MyApp.mWxApi.sendReq(req);
                }else {

                }
                break;
        }
    }

}
