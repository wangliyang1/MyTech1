package com.wd.tech.view.consult;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.wd.tech.R;
import com.wd.tech.api.MyApp;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.PayWXBean;
import com.wd.tech.bean.PayZFBean;
import com.wd.tech.bean.consult.BuyVipBean;
import com.wd.tech.bean.consult.FindVipBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.MD5Util;
import com.wd.tech.view.activity.MainActivity;
import com.wd.tech.view.consult.adapter.FindVipAdapter;
import com.wd.tech.widget.PayResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava2.Result;

public class PayVipActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.vip_menu)
    ImageView vipMenu;
    @BindView(R.id.vip_name)
    TextView vipName;
    @BindView(R.id.pay_recycler)
    RecyclerView payRecycler;
    @BindView(R.id.pay_price)
    TextView payPrice;
    @BindView(R.id.pay_lin1)
    LinearLayout payLin1;
    @BindView(R.id.pay_wx)
    ImageView payWx;
    @BindView(R.id.pay_zfb)
    ImageView payZfb;
    @BindView(R.id.pay_rb_wx)
    RadioButton payRbWx;
    @BindView(R.id.pay_rb_zfb)
    RadioButton payRbZfb;
    @BindView(R.id.pay_rg)
    RadioGroup payRg;
    @BindView(R.id.pay_bt)
    Button payBt;
    int num = 1;
    private int uid;
    FindVipBean.ResultBean resultBean;
    private static final int SDK_PAY_FLAG = 3;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayVipActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayVipActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayVipActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    @Override
    protected void initData() {
        payRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.pay_rb_wx:
                        num = 1;
                        break;
                    case R.id.pay_rb_zfb:
                        num = 2;
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        uid = sp.getInt("uid", -1);
        mPresenter.getNoParams(MyUrls.FIND_VIP_COMMODITY, FindVipBean.class);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_pay_vip;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if ((o instanceof FindVipBean) && TextUtils.equals("0000", ((FindVipBean) o).getStatus())){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            FindVipAdapter findVipAdapter = new FindVipAdapter(((FindVipBean) o).getResult());
            payRecycler.setLayoutManager(gridLayoutManager);
            payRecycler.setAdapter(findVipAdapter);
            findVipAdapter.setListener(new OnRecyclerItemClickListener() {

                @Override
                public void onItemClick(String s) {
                    int i = Integer.parseInt(s);
                    List<FindVipBean.ResultBean> result = ((FindVipBean) o).getResult();
                    resultBean = result.get(i);
                    payPrice.setText(resultBean.getPrice()+"");
                }

                @Override
                public void onLongItemClick(String s) {

                }
            });
        }
        if (o instanceof BuyVipBean&&TextUtils.equals("0000",((BuyVipBean) o).getStatus())){
            if (num==1){
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("orderId",((BuyVipBean) o).getOrderId());//订单号
                hashMap.put("payType",1);//支付方式
                mPresenter.postDoParams(MyUrls.PAY_VIP, PayWXBean.class,hashMap);
            }else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("orderId",((BuyVipBean) o).getOrderId());//订单号
                hashMap.put("payType",2);//支付方式
                mPresenter.postDoParams(MyUrls.PAY_VIP, PayZFBean.class,hashMap);
            }
        }
        if (o instanceof PayWXBean&&TextUtils.equals("0000",((PayWXBean) o).getStatus())){
            Toast.makeText(this, ""+((PayWXBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            String appId = ((PayWXBean) o).getAppId();                  //应用id
            String nonceStr = ((PayWXBean) o).getNonceStr();            //随机字符串
            String packageValue = ((PayWXBean) o).getPackageValue();    //扩展字段
            String partnerId = ((PayWXBean) o).getPartnerId();          //微信支付商户号
            String prepayId = ((PayWXBean) o).getPrepayId();            //支付凭证
            String sign = ((PayWXBean) o).getSign();                    //签名
            String timeStamp = ((PayWXBean) o).getTimeStamp();          //时间戳

            if (!MyApp.mWxApi.isWXAppInstalled()){
                Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            }
            MyApp.mWxApi.registerApp(appId);
            PayReq payReq = new PayReq();
            payReq.appId = appId;
            payReq.nonceStr = nonceStr;
            payReq.packageValue = packageValue;
            payReq.partnerId = partnerId;
            payReq.prepayId = prepayId;
            payReq.sign = sign;
            payReq.timeStamp = timeStamp+"";
            MyApp.mWxApi.sendReq(payReq);
        }
        if (o instanceof PayZFBean&&TextUtils.equals("0000",((PayZFBean) o).getStatus())){//支付宝支付
            Toast.makeText(this, "正在调取支付宝，请稍后", Toast.LENGTH_SHORT).show();
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(PayVipActivity.this);
                    Map<String, String> result = alipay.payV2(((PayZFBean) o).getResult(),true);
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();


        }
    }

    @Override
    public void onFailure(Throwable e) {

    }



    @OnClick({R.id.vip_menu, R.id.pay_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vip_menu://返回上一页面
                finish();
                break;
            case R.id.pay_bt://开通会员 开始支付
                //创建订单
                String s1 = "1381" +resultBean.getCommodityId()+ "tech";
                String s11 = MD5Util.getInstance().MD5(s1);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("commodityId",resultBean.getCommodityId());
                hashMap.put("sign",s11);
                mPresenter.postDoParams(MyUrls.BUY_VIP, BuyVipBean.class,hashMap);
                break;
        }
    }
}
