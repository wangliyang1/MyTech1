package com.wd.tech.view.consult.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.consult.FindByTitleBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.contract.Search;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.consult.ConsultDetailsActivity;
import com.wd.tech.view.consult.adapter.FindTitleAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * date:2020/4/22 19:53
 * author:王黎杨(Administrator)
 * function:
 */
public class FindSourceFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.find_recycler)
    RecyclerView findRecycler;
    List<FindByTitleBean.ResultBean> result = new ArrayList<>();
    @BindView(R.id.find_image)
    ImageView findImage;

    @Override
    protected void initView(View view) {
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {//写个判断，相对安全一点
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_find_source;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)//第2步:注册一个在后台线程执行的方法,用于接收事件
    public void onUserEvent(Search event) {
        if (event.getName() != null) {
            result.clear();
            HashMap<String, Object> map = new HashMap<>();
            map.put("source", event.getName());
            map.put("page", 1);
            map.put("count", 5);
            mPresenter.getDoParams(MyUrls.CONSULT_FIND_SOURCE, FindByTitleBean.class, map);
        } else {
            Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FindByTitleBean && TextUtils.equals("0000", ((FindByTitleBean) o).getStatus())) {
            if (((FindByTitleBean) o).getResult().size() == 0) {
                findImage.setVisibility(View.VISIBLE);
                findRecycler.setVisibility(View.GONE);
            } else {
                findImage.setVisibility(View.GONE);
                findRecycler.setVisibility(View.VISIBLE);
                List<FindByTitleBean.ResultBean> result = ((FindByTitleBean) o).getResult();
                List<FindByTitleBean.ResultBean> newresult = ((FindByTitleBean) o).getResult();
                result.addAll(newresult);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                FindTitleAdapter findTitleAdapter = new FindTitleAdapter(result);
                findRecycler.setLayoutManager(linearLayoutManager);
                findRecycler.setAdapter(findTitleAdapter);
                findTitleAdapter.setListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(String s) {
                        int i = Integer.parseInt(s);
                        Intent intent = new Intent(getActivity(), ConsultDetailsActivity.class);
                        intent.putExtra("id",i);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(String s) {

                    }
                });
            }

        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册Event

    }
}
