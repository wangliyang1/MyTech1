package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.consult.AddCommendBean;
import com.wd.tech.bean.consult.AddGreadBean;
import com.wd.tech.bean.consult.ConsultDetailsBean;
import com.wd.tech.bean.consult.FindCommentBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.consult.adapter.FindCommentAdapter;
import com.wd.tech.view.consult.adapter.InformationAdapter;
import com.wd.tech.view.consult.adapter.PlateAdapter;
import com.wd.tech.widget.MImageGetter;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultDetailsActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.details_titie)//资讯标题
            TextView detailsTitie;
    @BindView(R.id.details_time)//咨询时间
            TextView detailsTime;
    @BindView(R.id.details_source)//资讯来源
            TextView detailsSource;
    @BindView(R.id.details_content)//资讯内容
            WebView detailsContent;
    @BindView(R.id.details_plate)//资讯模块
            RecyclerView detailsPlate;
    @BindView(R.id.details_information)//资讯相关板块
            RecyclerView detailsInformation;
    @BindView(R.id.details_commentlist)//所有评论
            RecyclerView detailsCommentlist;
    @BindView(R.id.details_back)//返回上一页面
            ImageView detailsBack;
    @BindView(R.id.details_text)//评论
            TextView detailsText;
    @BindView(R.id.details_xiaoxi_image)//消息
            ImageView detailsXiaoxiImage;
    @BindView(R.id.details_xiaoxi_text)//消息人数
            TextView detailsXiaoxiText;
    @BindView(R.id.details_zan_image)//点赞
            ImageView detailsZanImage;
    @BindView(R.id.details_zan_text)//点赞人数
            TextView detailsZanText;
    @BindView(R.id.details_guan_image)//关注
            ImageView detailsGuanImage;
    @BindView(R.id.details_fen_image)//分享
            ImageView detailsFenImage;
    @BindView(R.id.details_fen_text)//分享人数
            TextView detailsFenText;
    @BindView(R.id.details_lie)//列表的布局
            LinearLayout detailsLie;
    @BindView(R.id.details_edit)//输入
            EditText detailsEdit;
    @BindView(R.id.details_fabiao)//发表
            TextView detailsFabiao;
    @BindView(R.id.details_relat)//评论布局
            RelativeLayout detailsRelat;
    @BindView(R.id.detaild_linear)
    LinearLayout detaildLinear;
    @BindView(R.id.details_scroll)
    ScrollView detailsScroll;
    @BindView(R.id.details_pay_image)
    ImageView detailsPayImage;
    @BindView(R.id.details_pay_bt)
    Button detailsPayBt;
    @BindView(R.id.pay_one)
    TextView payOne;
    @BindView(R.id.pay_two)
    TextView payTwo;
    @BindView(R.id.pay_cancel)
    ImageView payCancel;
    @BindView(R.id.pay_jifen)
    ImageView payJifen;
    @BindView(R.id.pay_huiyuan)
    ImageView payHuiyuan;
    @BindView(R.id.details_pay_relalayout)
    RelativeLayout detailsPayRelalayout;
    private int id;
    private ConsultDetailsBean.ResultBean result;

    @Override
    protected void initData() {
        detailsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (TextUtils.isEmpty(s1)) {
                    detailsEdit.setHint("既然来了,就写点什么吧");
                }
            }
        });
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            //请求资讯数据
            id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            mPresenter.getDoParams(MyUrls.CONSULT_INFO_DATAILS, ConsultDetailsBean.class, map);
            //请求评论数据
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("infoId", id);
            hashMap.put("page", 1);
            hashMap.put("count", 5);
            mPresenter.getDoParams(MyUrls.CONSULT_FIND_COMMENT, FindCommentBean.class, hashMap);
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
            result = ((ConsultDetailsBean) o).getResult();
            //标题
            detailsTitie.setText(result.getTitle());
            //日期
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = simpleDateFormat1.format(result.getReleaseTime());
            detailsTime.setText(format);
            //来源
            detailsSource.setText(result.getSource());
            if (result.getReadPower() == 1) {//判断是否有阅读权限
                String content = result.getContent();
                //主题
                detailsContent.getSettings().setJavaScriptEnabled(true);
                content = content.replaceAll("&", "");
                content = content.replaceAll("“", "\"");
                content = content.replaceAll("<", "<");
                content = content.replaceAll(">", ">");
                content = content.replaceAll("\\n", "<br>");
                content = content.replaceAll("<img", "<img width=\"100%\"");
                detailsContent.loadDataWithBaseURL(null,content,"text/html","utf-8",null);
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
            } else {
                detailsPayBt.setVisibility(View.VISIBLE);
                detailsPayImage.setVisibility(View.VISIBLE);
                detailsContent.setVisibility(View.GONE);
                detailsPlate.setVisibility(View.GONE);
                detailsInformation.setVisibility(View.GONE);
            }


            //设置最下方数据
            if (result.getWhetherGreat() == 1) {
                detailsZanImage.setImageResource(R.drawable.dianzan);
            } else {
                detailsZanImage.setImageResource(R.drawable.dianzan_null);
            }
            if (result.getWhetherCollection() == 1) {
                detailsGuanImage.setImageResource(R.drawable.guanzhu);
            } else {
                detailsGuanImage.setImageResource(R.drawable.guanzhu_null);
            }
            detailsXiaoxiText.setText(result.getComment() + "");//当前评论人数
            detailsZanText.setText(result.getPraise() + "");//当前点赞人数
        }
        if (o instanceof FindCommentBean && TextUtils.equals("0000", ((FindCommentBean) o).getStatus())) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            FindCommentAdapter findCommentAdapter = new FindCommentAdapter(((FindCommentBean) o).getResult());
            detailsCommentlist.setLayoutManager(linearLayoutManager);
            detailsCommentlist.setAdapter(findCommentAdapter);
        }
        if (o instanceof AddCommendBean) {//评论
            if (TextUtils.equals("0000", ((AddCommendBean) o).getStatus())) {
                Toast.makeText(this, "" + ((AddCommendBean) o).getMessage(), Toast.LENGTH_SHORT).show();
                //重新请求评论数据
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("infoId", id);
                hashMap.put("page", 1);
                hashMap.put("count", 5);
                mPresenter.getDoParams(MyUrls.CONSULT_FIND_COMMENT, FindCommentBean.class, hashMap);
            } else {
                Toast.makeText(this, "" + ((AddCommendBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        if (o instanceof AddGreadBean) {//点赞
            if (TextUtils.equals("0000", ((AddGreadBean) o).getStatus())) {
                Toast.makeText(this, "" + ((AddGreadBean) o).getMessage(), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "" + ((AddGreadBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    private int zan = 1;
    private int xin = 1;

    @OnClick({R.id.details_back, R.id.details_text, R.id.details_xiaoxi_image, R.id.details_zan_image, R.id.details_guan_image, R.id.details_fen_image, R.id.details_fabiao,R.id.pay_cancel, R.id.pay_jifen, R.id.pay_huiyuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.details_back://返回上一页面
                finish();
                break;
            case R.id.details_text:
                detailsLie.setVisibility(View.GONE);
                detailsRelat.setVisibility(View.VISIBLE);
                break;
            case R.id.details_xiaoxi_image://滑动到页面最下方
                int height = detailsScroll.getMeasuredHeight();
                detailsScroll.scrollTo(0, height - 20);
                break;
            case R.id.details_zan_image:
                if (zan == 1) {
                    detailsZanImage.setImageResource(R.drawable.dianzan);
                    zan = 2;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.postDoParams(MyUrls.CONSULT_ADD_GREAT, AddGreadBean.class, hashMap);
                    result.setWhetherGreat(1);
                } else {
                    detailsZanImage.setImageResource(R.drawable.dianzan_null);
                    zan = 1;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.dltDoParams(MyUrls.CONSULT_CANCLE_GREAT, AddGreadBean.class, hashMap);
                    result.setWhetherGreat(2);
                }
                break;
            case R.id.details_guan_image:
                if (xin == 1) {
                    detailsGuanImage.setImageResource(R.drawable.guanzhu);
                    xin = 2;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.postDoParams(MyUrls.CONSULT_ADD_COLLECTION, AddGreadBean.class, hashMap);
                    result.setWhetherCollection(1);
                } else {
                    detailsGuanImage.setImageResource(R.drawable.guanzhu_null);
                    xin = 1;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("infoId", id);
                    mPresenter.dltDoParams(MyUrls.CONSULT_CANCLE_COLLECTION, AddGreadBean.class, hashMap);
                    result.setWhetherCollection(2);
                }
                break;
            case R.id.details_fen_image://分享

                break;
            case R.id.details_fabiao:
                String trim = detailsEdit.getText().toString().trim();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("content", trim);
                hashMap.put("infoId", id);
                mPresenter.postDoParams(MyUrls.CONSULT_ADD_COMMENT, AddCommendBean.class, hashMap);
                detailsLie.setVisibility(View.VISIBLE);
                detailsRelat.setVisibility(View.GONE);
                break;
            case R.id.pay_cancel://隐藏付费方式选择页面
                detailsLie.setVisibility(View.VISIBLE);
                detailsPayRelalayout.setVisibility(View.GONE);
                break;
            case R.id.pay_jifen://跳转到积分兑换页面
                Intent intent = new Intent(this,PayIntegralActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.pay_huiyuan://跳转到购买会员页面
                Intent intent1 = new Intent(this,PayVipActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @OnClick(R.id.details_pay_bt)
    public void onViewClicked() {//点击弹出 付费框
        detailsLie.setVisibility(View.GONE);
        detailsPayRelalayout.setVisibility(View.VISIBLE);
    }

}
