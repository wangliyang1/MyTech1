package com.wd.tech.view.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.community.CommunityFlimBean;
import com.wd.tech.bean.community.CommunityListBean;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.commuity.CommUserActivity;
import com.wd.tech.view.activity.commuity.WriteFilmActivity;
import com.wd.tech.view.activity.commuity.WritePostActivity;
import com.wd.tech.view.adapter.commuity.CommuityAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 社区
* */
public class CommunityFragment extends BaseFragment<TechPresenter> {
    private PopupWindow mPopWindow;
    @BindView(R.id.com_rc)
    RecyclerView comRc;
    @BindView(R.id.write)
    FloatingActionButton write;
    private int page = 1;

    @Override
    protected void initView(View view) {
        comRc.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("count", 10);
        mPresenter.getDoParams(MyUrls.BASE_COMMUNITYLIST, CommunityListBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        //列表
        if (o instanceof CommunityListBean && TextUtils.equals("0000", ((CommunityListBean) o).getStatus())) {
            List<CommunityListBean.ResultBean> result = ((CommunityListBean) o).getResult();
            CommuityAdapter adapter = new CommuityAdapter(result);
            adapter.setOnClickListener(new CommuityAdapter.OnClickListener() {
                @Override
                public void onClick(int tag, int position) {
                    switch (tag) {
                        case 0:
                            //评论
                            showPopupWindow(position);
                            break;
                        case 1:
                            //点赞
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("communityId", result.get(position).getId());
                            if (result.get(position).getWhetherGreat() == 1) {
                                mPresenter.dltDoParams(MyUrls.BASE_DELETE_ZAN, CommunityZanBean.class, map);
                            } else {
                                mPresenter.postDoParams(MyUrls.BASE_COMMUNITY_ZAN, CommunityZanBean.class, map);
                            }
                            break;
                        case 2:
                            //更多评论
                            CommunityListBean.ResultBean resultBean = result.get(position);
                            String headPic = resultBean.getHeadPic();
                            String nickName = resultBean.getNickName();
                            int id = resultBean.getId();
                            Intent intent = new Intent(getContext(), WriteFilmActivity.class);
                            intent.putExtra("head",headPic);
                            intent.putExtra("name",nickName);
                            intent.putExtra("id",id);
                            startActivity(intent);
                            break;
                        case 3:
                            //查询用户帖子
                            Intent user = new Intent(getContext(), CommUserActivity.class);
                            user.putExtra("uid",position);
                            startActivity(user);
                            break;
                    }
                }
            });
            comRc.setAdapter(adapter);
        }
        //点赞取消赞
        if (o instanceof CommunityZanBean && TextUtils.equals("0000", ((CommunityZanBean) o).getStatus())) {
            Toast.makeText(getContext(), ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            HashMap<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("count", 10);
            mPresenter.getDoParams(MyUrls.BASE_COMMUNITYLIST, CommunityListBean.class, map);
        }
        //写评论
        if (o instanceof CommunityFlimBean &&TextUtils.equals("0000",((CommunityFlimBean) o).getStatus())){
            Toast.makeText(getContext(), ((CommunityFlimBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            HashMap<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("count", 10);
            mPresenter.getDoParams(MyUrls.BASE_COMMUNITYLIST, CommunityListBean.class, map);
        }
    }
    @OnClick(R.id.write)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), WritePostActivity.class);
        startActivity(intent);
    }
    @Override
    public void onFailure(Throwable e) {

    }
    //弹出框
    private void showPopupWindow(int id) {
        //加载布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup, null);
        mPopWindow = new PopupWindow(view,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(view);
        //设置各个控件的点击响应
        final EditText editText = view.findViewById(R.id.et);
        Button btn = view.findViewById(R.id.bt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString().trim();
                if (TextUtils.isEmpty(str)){
                    Toast.makeText(getContext(), "评论为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("communityId",id);
                map.put("content",str);
                mPresenter.postDoParams(MyUrls.BASE_FILM, CommunityFlimBean.class,map);
                mPopWindow.dismiss();//让PopupWindow消失
            }
        });
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
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}
