package com.wd.tech.view.activity.commuity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommUser0Bean;
import com.wd.tech.bean.community.CommUserBean;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.UserFollowAdapter;
import com.wd.tech.view.adapter.commuity.CommUserAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommUserActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.swrc)
    SwipeMenuRecyclerView swrc;
    @BindView(R.id.rc)
    RecyclerView rc;
    private int uid;
    private Dialog dialog;
    private CommUser0Bean.ResultBean.CommunityUserVoBean communityUserVo;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        rc.setLayoutManager(new LinearLayoutManager(this));
        swrc.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (intent != null) {
            uid = intent.getIntExtra("uid", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("fromUid", uid);
            map.put("page",1);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_USER_COM, CommUserBean.class,map);
        }
        changePic();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_comm_user;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CommUser0Bean &&TextUtils.equals("0000",((CommUser0Bean) o).getStatus())){
            List<CommUser0Bean.ResultBean> result = ((CommUser0Bean) o).getResult();
            communityUserVo = result.get(0).getCommunityUserVo();
            UserFollowAdapter followAdapter = new UserFollowAdapter(communityUserVo);
            followAdapter.setOnClickListener(new UserFollowAdapter.OnClickListener() {
                @Override
                public void onClick(String headpic) {
                    setDialog(headpic);
                    dialog.show();
                }
            });
            //添加侧滑按钮
            swrc.setSwipeMenuCreator(new SwipeMenuCreator() {
                @Override
                public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                    SwipeMenuItem addFriendItem = new SwipeMenuItem(CommUserActivity.this)
                            .setBackground(R.drawable.blue)
                            .setText("+好友")
                            .setHeight(60)//设置高，这里使用match_parent，就是与item的高相同
                            .setWidth(100);//设置宽
                    swipeRightMenu.addMenuItem(addFriendItem);//设置右边的侧滑
                    SwipeMenuItem deleteItem = new SwipeMenuItem(CommUserActivity.this)
                            .setBackground(R.drawable.red)
                            .setText("+关注")
                            .setHeight(60)
                            .setWidth(100);//设置宽
                    swipeRightMenu.addMenuItem(deleteItem);
                }
            });
            //侧滑按钮点击操作
            swrc.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                @Override
                public void onItemClick(SwipeMenuBridge menuBridge) {
                    //不关闭可能导致菜单错乱
                    menuBridge.closeMenu();
                    int userId = CommUserActivity.this.communityUserVo.getUserId();
                }
            });
            swrc.setAdapter(followAdapter);
            Glide.with(ivTitle).load( this.communityUserVo.getHeadPic()).into(ivTitle);
        }
        if (o instanceof CommUserBean&& TextUtils.equals("0000",((CommUserBean) o).getStatus())){
            List<CommUserBean.ResultBean> result = ((CommUserBean) o).getResult();

            CommUserAdapter adapter = new CommUserAdapter(result.get(0).getCommunityUserPostVoList());
            adapter.setOnClickListener(new CommUserAdapter.OnClickListener() {
                @Override
                public void onClick(int tag, int tion) {
                    switch (tag){
                        case 0:
                            CommUserBean.ResultBean.CommunityUserPostVoListBean listBena = result.get(0).getCommunityUserPostVoList().get(tion);
                            setDialog(listBena.getFile());
                            dialog.show();
                            break;
                        case 1:
                            //点赞
                            CommUserBean.ResultBean.CommunityUserPostVoListBean resultBean1 = result.get(0).getCommunityUserPostVoList().get(tion);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("communityId", resultBean1.getId());
                            if (resultBean1.getWhetherGreat() == 1) {
                                mPresenter.dltDoParams(MyUrls.BASE_DELETE_ZAN, CommunityZanBean.class, map);
                            } else {
                                mPresenter.postDoParams(MyUrls.BASE_COMMUNITY_ZAN, CommunityZanBean.class, map);
                            }
                            break;
                        case 2:
                            CommUserBean.ResultBean.CommunityUserPostVoListBean resultBean = result.get(0).getCommunityUserPostVoList().get(tion);
                            CommUserBean.ResultBean.CommunityUserVoBean userVo = result.get(0).getCommunityUserVo();
                            String headPic = userVo.getHeadPic();
                            String nickName = userVo.getNickName();
                            int id = resultBean.getId();
                            Intent intent = new Intent(CommUserActivity.this, WriteFilmActivity.class);
                            intent.putExtra("head",headPic);
                            intent.putExtra("name",nickName);
                            intent.putExtra("id",id);
                            startActivity(intent);
                            break;
                    }
                }
            });
            rc.setAdapter(adapter);
        }
        //点赞取消赞
        if (o instanceof CommunityZanBean && TextUtils.equals("0000", ((CommunityZanBean) o).getStatus())) {
            Toast.makeText(CommUserActivity.this, ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            HashMap<String, Object> map = new HashMap<>();
            map.put("fromUid", uid);
            map.put("page",1);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_USER_COM, CommUserBean.class,map);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    private void changePic() {
        //展示在dialog上面的大图
        dialog = new Dialog(CommUserActivity.this, R.style.PicActivity);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);
    }
    public void setDialog(String uri){
        ImageView image = getImageView(uri);
        dialog.setContentView(image);

        //大图的点击事件（点击让他消失）
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private ImageView getImageView(String uri){
        ImageView imageView = new ImageView(this);
        //宽高
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Glide.with(imageView).load(uri).into(imageView);
        return imageView;
    }
}
