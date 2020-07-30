package com.wd.tech.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stx.xhb.androidx.XBanner;
import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.consult.RecommendListBean;
import com.wd.tech.bean.consult.XbannerBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.view.adapter.ConsultAdapter;
import com.wd.tech.view.consult.ConsultDetailsActivity;
import com.wd.tech.view.consult.ConsultSearchActivity;
import com.wd.tech.view.consult.FindPlateActivity;
import com.wd.tech.view.consult.WebActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;


/*
 * 资讯
 * */
public class ConsultFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.consult_menu)
    ImageView consultMenu;
    @BindView(R.id.consult_search)
    ImageView consultSearch;
    @BindView(R.id.consult_xbanner)
    XBanner consultXbanner;
    @BindView(R.id.consult_banner_text)
    TextView consultBannerText;
    @BindView(R.id.consult_recycler)
    RecyclerView consultRecycler;

    @Override
    protected void initView(View view) {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.CONSULT_XBANNER, XbannerBean.class);//请求xbanner数据
        SharedPreferences sp = getActivity().getSharedPreferences("login.dp", MODE_PRIVATE);

            int uid = sp.getInt("uid", -1);
            String sid = sp.getString("sid", "");
            Log.i("TAG_ID",uid+"");
            Log.i("TAG_ID",sid+"");

            HashMap<String, Object> map = new HashMap<>();
            map.put("plateId",0);
            map.put("page",1);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.CONSULT_RECOMMEND_LIST, RecommendListBean.class,map);


    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        //轮播图
        if (o instanceof XbannerBean && TextUtils.equals("0000", ((XbannerBean) o).getStatus())) {
            List<XbannerBean.ResultBean> result = ((XbannerBean) o).getResult();
            consultXbanner.setBannerData(result);
            consultXbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    XbannerBean.ResultBean resultBean = result.get(position);
                    ImageView imageView = (ImageView) view;
                    GlideUtils.getPhoto(resultBean.getImageUrl(), imageView);
                    consultBannerText.setText(resultBean.getTitle());
                }
            });
        }
        //列表展示
        if (o instanceof RecommendListBean && TextUtils.equals("0000",((RecommendListBean) o).getStatus())){
            List<RecommendListBean.ResultBean> result = ((RecommendListBean) o).getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            ConsultAdapter consultAdapter = new ConsultAdapter(result);

            consultRecycler.setLayoutManager(linearLayoutManager);
            consultRecycler.setAdapter(consultAdapter);

            consultAdapter.setListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(String s) {
                    String[] split = s.split(",");
                    if (TextUtils.equals("1",split[1])){
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("content",split[2]);
                        intent.putExtra("url",split[3]);
                        startActivity(intent);
                    }else {
                        int i = Integer.parseInt(split[0]);
                        Intent intent = new Intent(getActivity(), ConsultDetailsActivity.class);
                        intent.putExtra("id",i);
                        startActivity(intent);
                    }
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

    @OnClick({R.id.consult_menu, R.id.consult_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.consult_menu://跳转频道选择页
                startActivity(new Intent(getContext(),FindPlateActivity.class));
                break;
            case R.id.consult_search://进入到搜索页面
                startActivity(new Intent(getContext(), ConsultSearchActivity.class));
                break;
        }
    }
}
