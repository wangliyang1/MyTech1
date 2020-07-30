package com.wd.tech.view.fragment.info;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.FriendNoticeBean;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.info.ChatMsgActivity;
import com.wd.tech.view.adapter.info.InfoItAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * date:2020/4/21 0021
 * author:王黎杨(Administrator)
 * function:
 */
public class InfoItFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.ifit_rc)
    SwipeMenuRecyclerView ifitRc;
    private int page=1;
    @Override
    protected void initView(View view) {
        ifitRc.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("count",10);
        mPresenter.getDoParams(MyUrls.BASE_FRIEND_NOTICE, FriendNoticeBean.class,map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_infoit;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FriendNoticeBean&&TextUtils.equals("0000",((FriendNoticeBean) o).getStatus())){
            if (((FriendNoticeBean) o).getResult().size()>0){
                List<FriendNoticeBean.ResultBean> result = ((FriendNoticeBean) o).getResult();
                InfoItAdapter infoItAdapter = new InfoItAdapter(result);
                infoItAdapter.setOnClickListener(new InfoItAdapter.OnClickListener() {
                    @Override
                    public void onClick(int friendId,String head,String nickName) {
                        Intent intent = new Intent(getContext(), ChatMsgActivity.class);
                        intent.putExtra("id",friendId);
                        intent.putExtra("head",head);
                        intent.putExtra("name",nickName);
                        startActivity(intent);
                    }
                });
                ifitRc.setSwipeMenuCreator(new SwipeMenuCreator() {
                    @Override
                    public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                        @SuppressLint("ResourceType") SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                                .setBackground(R.drawable.red)
                                .setText("删除")
                                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)//设置高，这里使用match_parent，就是与item的高相同
                                .setWidth(70);//设置宽
                        swipeRightMenu.addMenuItem(deleteItem);//设置右边的侧滑
                    }
                });
                ifitRc.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                    @Override
                    public void onItemClick(SwipeMenuBridge menuBridge) {
                        menuBridge.closeMenu();
                        int adapterPosition = menuBridge.getAdapterPosition();
                        int fromUid = result.get(adapterPosition).getFromUid();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("fromUid",fromUid);
                        mPresenter.dltDoParams(MyUrls.BASE_DELETE_FRIENDINFO, CommunityZanBean.class,map);
                        result.remove(adapterPosition);
                        infoItAdapter.notifyDataSetChanged();

                    }
                });
                ifitRc.setAdapter(infoItAdapter);
            }
        }
        if (o instanceof CommunityZanBean&&TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(getContext(), ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
