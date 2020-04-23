package com.wd.tech.view.consult;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wd.tech.R;
import com.wd.tech.api.MyApp;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.consult.ConsultDetailsBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.view.consult.adapter.InformationAdapter;
import com.wd.tech.view.consult.adapter.PlateAdapter;
import com.wd.tech.widget.MImageGetter;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;

public class ConsultDetailsActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.details_titie)
    TextView detailsTitie;
    @BindView(R.id.details_time)
    TextView detailsTime;
    @BindView(R.id.details_source)
    TextView detailsSource;
    @BindView(R.id.details_content)
    TextView detailsContent;
    @BindView(R.id.details_plate)
    RecyclerView detailsPlate;
    @BindView(R.id.details_information)
    RecyclerView detailsInformation;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            mPresenter.getDoParams(MyUrls.CONSULT_INFO_DATAILS, ConsultDetailsBean.class, map);
        }


    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_consult_details;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof ConsultDetailsBean && TextUtils.equals("0000", ((ConsultDetailsBean) o).getStatus())) {
            ConsultDetailsBean.ResultBean result = ((ConsultDetailsBean) o).getResult();
            //标题
            detailsTitie.setText(result.getTitle());
            //日期
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = simpleDateFormat1.format(result.getReleaseTime());
            detailsTime.setText(format);

          /*  Document parse = Jsoup.parse(content);
            Elements img = parse.getElementsByTag("img");
            GlideUtils.getPhoto(img.get(0).attr("src"), detailsImage);
            String clean = Jsoup.clean(content, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));*/
            //主题
            detailsContent.setText(Html.fromHtml(result.getContent(),new MImageGetter(this,detailsContent),null));
            //来源
            detailsSource.setText(result.getSource());
            //所属板块
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            PlateAdapter plateAdapter = new PlateAdapter(result.getPlate());
            detailsPlate.setLayoutManager(linearLayoutManager);
            detailsPlate.setAdapter(plateAdapter);
            //推荐阅读
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
            linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
            InformationAdapter informationAdapter = new InformationAdapter(result.getInformationList());
            detailsInformation.setLayoutManager(linearLayoutManager1);
            detailsInformation.setAdapter(informationAdapter);
            informationAdapter.setListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(String s) {
                    int i = Integer.parseInt(s);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", i);
                    mPresenter.getDoParams(MyUrls.CONSULT_INFO_DATAILS, ConsultDetailsBean.class, map);
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
