package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.consult.FindPlaterBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.consult.adapter.FindPlateAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *
 * */
public class FindPlateActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.plate_recycler)
    RecyclerView plateRecycler;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        mPresenter.getNoParams(MyUrls.CONSULT_FIND_PLATE, FindPlaterBean.class);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_find_plate;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
       if (o instanceof FindPlaterBean && TextUtils.equals("0000",((FindPlaterBean) o).getStatus())){
           GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
           FindPlateAdapter findPlateAdapter = new FindPlateAdapter(((FindPlaterBean) o).getResult());
           plateRecycler.setLayoutManager(gridLayoutManager);
           plateRecycler.setAdapter(findPlateAdapter);

           findPlateAdapter.setListener(new OnRecyclerItemClickListener() {
               @Override
               public void onItemClick(String s) {
                   int i = Integer.parseInt(s);
                   Intent intent = new Intent(FindPlateActivity.this,ChannelActivity.class);
                   intent.putExtra("name",((FindPlaterBean) o).getResult().get(i).getName());
                   intent.putExtra("id",((FindPlaterBean) o).getResult().get(i).getId());
                   startActivity(intent);
               }

               @Override
               public void onLongItemClick(String s) {

               }
           });

       }
    }

    @Override
    public void onFailure(Throwable e) {

    }

}
