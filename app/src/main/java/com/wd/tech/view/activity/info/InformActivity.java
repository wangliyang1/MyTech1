package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.bean.info.GroupNoticeBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.GroupNoticeAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

//群通知
public class InformActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.rc)
    RecyclerView rc;
    int page=1;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        rc.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("count",10);
        mPresenter.getDoParams(MyUrls.BASE_GROUP_NOTICE, GroupNoticeBean.class,map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_inform;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        //通知列表
        if (o instanceof GroupNoticeBean&& TextUtils.equals("0000",((GroupNoticeBean) o).getStatus())){
            List<GroupNoticeBean.ResultBean> result = ((GroupNoticeBean) o).getResult();
            if (result.size()==0){
                Toast.makeText(this, "没有通知", Toast.LENGTH_SHORT).show();
                rc.setVisibility(View.GONE);
                return;
            }else {
                GroupNoticeAdapter adapter = new GroupNoticeAdapter(result);
                adapter.setOnClickListener(new GroupNoticeAdapter.OnClickListener() {
                    @Override
                    public void onClick(int id, int flag) {
                        //审核群申请
                        HashMap<String,Object> map=new HashMap<>();
                        map.put("noticeId",id);
                        map.put("flag",flag);
                        mPresenter.putDoParams(MyUrls.BASE_AUDIT, CommunityZanBean.class,map);
                    }
                });
                rc.setAdapter(adapter);
            }
        }
        //审核
        if (o instanceof CommunityZanBean&& TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            //刷新数据
            HashMap<String,Object> map=new HashMap<>();
            map.put("page",page);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_GROUP_NOTICE, GroupNoticeBean.class,map);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
