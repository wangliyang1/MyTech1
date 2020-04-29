package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.UserIntegralBean;
import com.wd.tech.bean.consult.ByIntegralBean;
import com.wd.tech.bean.consult.ConsultDetailsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * 积分兑换页面
 * */
public class PayIntegralActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.integral_menu)//返回上一页面
    ImageView integralMenu;
    @BindView(R.id.integral_name)
    TextView integralName;
    @BindView(R.id.integral_image)//资讯缩略图
    ImageView integralImage;
    @BindView(R.id.integral_title)//资讯标题
    TextView integralTitle;
    @BindView(R.id.integral_summary)//资讯主题
    TextView integralSummary;
    @BindView(R.id.integral_source)//资讯来源
    TextView integralSource;
    @BindView(R.id.integral_time)//资讯时间
    TextView integralTime;
    @BindView(R.id.integral_collection_image)//关注图片
    ImageView integralCollectionImage;
    @BindView(R.id.integral_collection)//关注人数
    TextView integralCollection;
    @BindView(R.id.integral_share_image)//分享图片
    ImageView integralShareImage;
    @BindView(R.id.integral_share)//分享人数
    TextView integralShare;
    @BindView(R.id.item_rela2)//资讯布局
    RelativeLayout itemRela2;
    @BindView(R.id.integral_pay)//购买所需积分
    TextView integralPay;
    @BindView(R.id.lin1_integral)//
    LinearLayout lin1Integral;
    @BindView(R.id.integral_me)//我目前的积分
    TextView integralMe;
    @BindView(R.id.integral_cancel_chengg)//成功—取消
    ImageView integralCancelChengg;
    @BindView(R.id.integral_jixu)//成功-继续阅读
    Button integralJixu;
    @BindView(R.id.chenggong)//成功布局
    RelativeLayout chenggong;
    @BindView(R.id.integral_cancel)//失败—取消
    ImageView integralCancel;
    @BindView(R.id.integral_quxiao)//失败-取消按钮
    Button integralQuxiao;
    @BindView(R.id.integral_quzhuan)//失败-去赚积分
    Button integralQuzhuan;
    @BindView(R.id.shibai)//失败布局
    RelativeLayout shibai;
    @BindView(R.id.integral_bt)//兑换资讯
    Button integralBt;
    private int id;
    private ConsultDetailsBean.ResultBean result;

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_pay_integral;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            mPresenter.getDoParams(MyUrls.CONSULT_INFO_DATAILS, ConsultDetailsBean.class, map);
            mPresenter.getNoParams(MyUrls.FIND_USER_INTEGRAL, UserIntegralBean.class);
        }
        //开始兑换积分
        integralBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("infoId",id);
                hashMap.put("integralCost",result.getIntegralCost());
                mPresenter.postDoParams(MyUrls.CONSULT_PAY_INTEGRAL, ByIntegralBean.class,hashMap);
            }
        });
    }


    @Override
    public void onSuccess(Object o) {
        if (o instanceof ConsultDetailsBean && TextUtils.equals("0000",((ConsultDetailsBean) o).getStatus())){
            result = ((ConsultDetailsBean) o).getResult();
            String thumbnail = result.getThumbnail();
            String[] split = thumbnail.split("？");

            GlideUtils.getPhoto(split[0],integralImage);

            integralTitle.setText(result.getTitle());//标题
            integralSummary.setText(result.getSummary());//主题
            integralSource.setText(result.getSource());//来源
            //日期
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = simpleDateFormat1.format(result.getReleaseTime());
            integralTime.setText(format);
            //点赞数据设置
            if (result.getWhetherCollection()==1){
                integralCollectionImage.setImageResource(R.drawable.guanzhu);
            }else {
                integralCollectionImage.setImageResource(R.drawable.guanzhu_null);
            }
            integralCollection.setText(result.getPraise()+"");
            //分享
            integralShare.setText(result.getShare()+"");
            //兑换所需积分
            integralPay.setText(result.getIntegralCost()+"");
        }
        if (o instanceof UserIntegralBean && TextUtils.equals("0000",((UserIntegralBean) o).getStatus())){
            integralMe.setText(((UserIntegralBean) o).getResult().getAmount()+"");
        }
        if (o instanceof ByIntegralBean){
            if (TextUtils.equals("0000",((ByIntegralBean) o).getStatus())){
                //成功则显示成功布局隐藏失败布局
                chenggong.setVisibility(View.VISIBLE);
                shibai.setVisibility(View.GONE);

                //继续阅读则关闭当前页面，返回上一页面
                integralJixu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PayIntegralActivity.this, ConsultDetailsActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                        finish();
                    }
                });
                //x 则隐藏布局
                integralCancelChengg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chenggong.setVisibility(View.GONE);
                    }
                });
            }else {
                //失败则显示 失败页面
                chenggong.setVisibility(View.GONE);
                shibai.setVisibility(View.VISIBLE);
                integralCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shibai.setVisibility(View.GONE);
                    }
                });
                integralQuxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shibai.setVisibility(View.GONE);
                    }
                });
                //去赚积分 进入积分页面
                integralQuzhuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

}
