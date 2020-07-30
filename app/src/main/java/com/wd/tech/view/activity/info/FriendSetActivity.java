package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.bean.info.FriendInfoByIdBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

//好友聊天设置页面
public class FriendSetActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.bei)
    TextView bei;
    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.delete)
    Button delete;
    private PopupWindow mPopWindow;
    private int id;
    private String nickName;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("friend", id);
            mPresenter.getDoParams(MyUrls.BASE_FRIENDINFO_ID, FriendInfoByIdBean.class,map);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_friend_set;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        //好友信息
        if (o instanceof FriendInfoByIdBean&& TextUtils.equals("0000",((FriendInfoByIdBean) o).getStatus())){
            FriendInfoByIdBean.ResultBean result = ((FriendInfoByIdBean) o).getResult();
            String headPic = result.getHeadPic();
            nickName = result.getNickName();
            String userName = result.getUserName();
            GlideUtils.getCiclePhoto(headPic,head);
            name.setText(nickName);
            bei.setText(userName);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeBack, R.id.clear, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack:
                finish();
                break;
            case R.id.clear:
                showPopupWindow(id,0,nickName);
                break;
            case R.id.delete:
                showPopupWindow(id,1,nickName);
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
        if (tag==0){//清空聊天记录
            title.setText("将清空此群聊与此好友的聊天记录");
            dl.setText("确定");
            dl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("friendUid",id);
                    mPresenter.dltDoParams(MyUrls.BASE_DELETE_HISTORY, CommunityZanBean.class,map);
                    mPopWindow.dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopWindow.dismiss();//让PopupWindow消失
                }
            });
        }else if (tag==1){//删除好友
            title.setText("将联系人“"+nickName+"”删除，同时删除与该联系人的聊天记录");
            dl.setText("删除好友");
            dl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("friendUid",id);
                    mPresenter.dltDoParams(MyUrls.BASE_DELETE_FRIEND, CommunityZanBean.class,map);
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
