package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.bean.info.GroupDetailsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class GroupSetActivity extends BaseActivity<TechPresenter> {
    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.no_name)
    TextView noName;
    @BindView(R.id.no_num)
    TextView noNum;
    @BindView(R.id.no_chengyuan)
    RelativeLayout noChengyuan;
    @BindView(R.id.no_tongzhi)
    RelativeLayout noTongzhi;
    @BindView(R.id.no_bt)
    Button noBt;
    @BindView(R.id.no_main)
    LinearLayout noMain;
    @BindView(R.id.do_num)
    TextView doNum;
    @BindView(R.id.do_chengyuan)
    RelativeLayout doChengyuan;
    @BindView(R.id.do_name)
    TextView doName;
    @BindView(R.id.do_jianjie)
    RelativeLayout doJianjie;
    @BindView(R.id.do_tongzhi)
    RelativeLayout doTongzhi;
    @BindView(R.id.do_guanli)
    RelativeLayout doGuanli;
    @BindView(R.id.do_bt)
    Button doBt;
    @BindView(R.id.do_main)
    LinearLayout doMain;
    private SharedPreferences sp;
    private int userId;
    private int id;
    private String name;
    private int tag=0;
    private PopupWindow mPopWindow;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        userId = sp.getInt("uid", -1);
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("groupId", id);
            mPresenter.getDoParams(MyUrls.BASE_GROUP_DETAILS, GroupDetailsBean.class, map);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_group_set;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof GroupDetailsBean && TextUtils.equals("0000", ((GroupDetailsBean) o).getStatus())) {
            GroupDetailsBean.ResultBean result = ((GroupDetailsBean) o).getResult();
            int ownerUid = result.getOwnerUid();
            name = result.getGroupName();
            //是否是群主
            if (ownerUid == userId) {//是群主
                noName.setVisibility(View.GONE);
                noMain.setVisibility(View.GONE);
                doMain.setVisibility(View.VISIBLE);
                doName.setText(name);
                doNum.setText("共"+result.getCurrentCount()+"人");
                GlideUtils.getPhoto(result.getGroupImage(),head);
            } else {//不是群主
                tag=1;
                noName.setVisibility(View.VISIBLE);
                noMain.setVisibility(View.VISIBLE);
                doMain.setVisibility(View.GONE);
                noName.setText(name);
                noNum.setText("共"+result.getCurrentCount()+"人");
                GlideUtils.getPhoto(result.getGroupImage(),head);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeBack, R.id.no_chengyuan, R.id.no_tongzhi, R.id.no_bt, R.id.do_chengyuan, R.id.do_name, R.id.do_jianjie, R.id.do_tongzhi, R.id.do_guanli, R.id.do_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack:
                finish();
                break;
            case R.id.no_chengyuan:
            case R.id.do_chengyuan:
            case R.id.do_guanli:
                //群管理/群成员
                Intent guanli = new Intent(this,GroupHumanActivity.class);
                guanli.putExtra("id",id);
                guanli.putExtra("tag",tag);
                startActivity(guanli);
                break;
            case R.id.no_tongzhi:
            case R.id.do_tongzhi:
                //群通知
                startActivity(this,InformActivity.class);
                break;
            case R.id.no_bt:
                //退出群组
                showPopupWindow(id,1,name);
                break;
            case R.id.do_name:
                break;
            case R.id.do_jianjie:
                //群简介
                Intent intent = new Intent(this,UpdateDesionActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.do_bt:
                //解散群
                showPopupWindow(id,0,name);
                break;
        }
    }
    //弹出框
    private void showPopupWindow(int id,int tag,String nickName) {
        //加载布局
        View view = LayoutInflater.from(this).inflate(R.layout.popclear, null);
        mPopWindow = new PopupWindow(view,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(view);
        //设置各个控件的点击响应
        TextView dl = view.findViewById(R.id.dl);
        TextView title = view.findViewById(R.id.title);
        TextView cancel = view.findViewById(R.id.cancel);
        if (tag==0){//解散群
            title.setText("你将解散"+nickName+"群?");
            dl.setText("确定");
            dl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String,Object> map1=new HashMap<>();
                    map1.put("groupId",id);
                    mPresenter.dltDoParams(MyUrls.BASE_DELETE_GROUP, CommunityZanBean.class,map1);
                    mPopWindow.dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopWindow.dismiss();//让PopupWindow消失
                }
            });
        }else if (tag==1){//退出群
            title.setText("你将退出"+nickName+"群?");
            dl.setText("确定");
            dl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("groupId",id);
                    mPresenter.dltDoParams(MyUrls.BASE_BACK_GROUP, CommunityZanBean.class,map);
                    mPopWindow.dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopWindow.dismiss();//让PopupWindow消失
                }
            });
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mPopWindow != null && mPopWindow.isShowing()) {
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }
                return false;
            }
        });

        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}
