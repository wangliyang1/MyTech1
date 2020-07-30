package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.bean.info.GroupAllUserListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.ChengAdapter;
import com.wd.tech.view.adapter.info.GuanAdapter;
import com.wd.tech.view.adapter.info.ZhuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//群成员/管理
public class GroupHumanActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.qunzhu)
    SwipeMenuRecyclerView qunzhu;
    @BindView(R.id.qunguanli)
    SwipeMenuRecyclerView qunguanli;
    @BindView(R.id.chengyuan)
    SwipeMenuRecyclerView chengyuan;
    private int id;
    private int tag;
    List<GroupAllUserListBean.ResultBean> zhu=new ArrayList<>();
    List<GroupAllUserListBean.ResultBean> guan=new ArrayList<>();
    List<GroupAllUserListBean.ResultBean> cheng=new ArrayList<>();
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            tag = intent.getIntExtra("tag", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("groupId", id);
            mPresenter.getDoParams(MyUrls.BASE_QUERY_ALLUSERS, GroupAllUserListBean.class, map);
        }
        qunzhu.setLayoutManager(new LinearLayoutManager(this));
        qunguanli.setLayoutManager(new LinearLayoutManager(this));
        chengyuan.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_group_human;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof GroupAllUserListBean && TextUtils.equals("0000", ((GroupAllUserListBean) o).getStatus())) {
            List<GroupAllUserListBean.ResultBean> result = ((GroupAllUserListBean) o).getResult();
            for (int i = 0; i < result.size(); i++) {//分类
                GroupAllUserListBean.ResultBean resultBean = result.get(i);
                switch (resultBean.getRole()){
                    case 1://普通用户
                        cheng.add(resultBean);
                        break;
                    case 2://管理
                        guan.add(resultBean);
                        break;
                    case 3://创建人
                        zhu.add(resultBean);
                        break;
                }
            }
            //设置适配器
            qunzhu.setAdapter(new ZhuAdapter(zhu));
            GuanAdapter guanAdapter = new GuanAdapter(guan);
            ChengAdapter chengAdapter = new ChengAdapter(cheng);
            if (tag==0){
                qunguanli.setSwipeMenuCreator(new SwipeMenuCreator() {
                    @Override
                    public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                        SwipeMenuItem addFriendItem = new SwipeMenuItem(GroupHumanActivity.this)
                                .setBackground(R.drawable.blue)
                                .setText("移出群管理")
                                .setHeight(50)//设置高，这里使用match_parent，就是与item的高相同
                                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽
                        swipeRightMenu.addMenuItem(addFriendItem);//设置右边的侧滑
                        SwipeMenuItem deleteItem = new SwipeMenuItem(GroupHumanActivity.this)
                                .setBackground(R.drawable.red)
                                .setText("移除")
                                .setHeight(50)
                                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽
                        swipeRightMenu.addMenuItem(deleteItem);
                    }
                });
                qunguanli.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                    @Override
                    public void onItemClick(SwipeMenuBridge menuBridge) {
                        menuBridge.closeMenu();
                        int direction = menuBridge.getDirection();// 左侧还是右侧菜单。0是左，右是1
                        int adapterPosition = menuBridge.getAdapterPosition();//条目的位置position
                        int menuPosition = menuBridge.getPosition();//菜单按钮的位置
                        GroupAllUserListBean.ResultBean resultBean = guan.get(adapterPosition);
                        int userId = resultBean.getUserId();
                        if (menuPosition==0){//移除群管理
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("groupId",id);
                            map.put("groupUserId",userId);
                            map.put("role",1);
                            mPresenter.putDoParams(MyUrls.BASE_UPDATE_GROUP, CommunityZanBean.class,map);
                            guan.remove(adapterPosition);
                            cheng.add(resultBean);
                            guanAdapter.notifyDataSetChanged();
                            chengAdapter.notifyDataSetChanged();
                        }else {//移除群成员
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("groupId",id);
                            map.put("groupUserId",userId);
                            mPresenter.dltDoParams(MyUrls.BASE_DETELE_GROUPUSER,CommunityZanBean.class,map);
                            guan.remove(adapterPosition);
                            guanAdapter.notifyDataSetChanged();
                        }
                    }
                });
                chengyuan.setSwipeMenuCreator(new SwipeMenuCreator() {
                    @Override
                    public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                        SwipeMenuItem addFriendItem = new SwipeMenuItem(GroupHumanActivity.this)
                                .setBackground(R.drawable.blue)
                                .setText("添加群管理")
                                .setHeight(50)//设置高，这里使用match_parent，就是与item的高相同
                                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽
                        swipeRightMenu.addMenuItem(addFriendItem);//设置右边的侧滑
                        SwipeMenuItem deleteItem = new SwipeMenuItem(GroupHumanActivity.this)
                                .setBackground(R.drawable.red)
                                .setText("移除")
                                .setHeight(50)
                                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽
                        swipeRightMenu.addMenuItem(deleteItem);
                    }
                });
                chengyuan.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                    @Override
                    public void onItemClick(SwipeMenuBridge menuBridge) {
                        menuBridge.closeMenu();
                        int adapterPosition = menuBridge.getAdapterPosition();
                        int menuPosition = menuBridge.getPosition();
                        GroupAllUserListBean.ResultBean resultBean = cheng.get(adapterPosition);
                        int userId = resultBean.getUserId();
                        if (menuPosition==0){//添加群管理
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("groupId",id);
                            map.put("groupUserId",userId);
                            map.put("role",2);
                            mPresenter.putDoParams(MyUrls.BASE_UPDATE_GROUP, CommunityZanBean.class,map);
                            cheng.remove(adapterPosition);
                            guan.add(resultBean);
                            guanAdapter.notifyDataSetChanged();
                            chengAdapter.notifyDataSetChanged();
                        }else {//移除群成员
                            HashMap<String,Object> map=new HashMap<>();
                            map.put("groupId",id);
                            map.put("groupUserId",userId);
                            mPresenter.dltDoParams(MyUrls.BASE_DETELE_GROUPUSER,CommunityZanBean.class,map);
                            cheng.remove(adapterPosition);
                            chengAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
            qunguanli.setAdapter(guanAdapter);
            chengyuan.setAdapter(chengAdapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeBack, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack:
                finish();
                break;
            case R.id.add://邀请进群
                Intent intent = new Intent(this,InviteActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
        }
    }
}
