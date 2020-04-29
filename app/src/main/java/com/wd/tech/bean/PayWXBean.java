package com.wd.tech.bean;

/**
 * date:2020/4/26 17:13
 * author:王黎杨(Administrator)
 * function:微信支付
 * */
public class PayWXBean {

    /**
     * appId : wx4c96b6b8da494224
     * mchKey : W1u2h3u4a5j6i7a8n9x1u2y3u4n5j678
     * message : 支付成功
     * nonceStr : YKp1RpekjB85Ngbz
     * packageValue : Sign=WXPay
     * partnerId : 1513586931
     * prepayId : wx26171315903598fee8bf5f0c1452637900
     * sign : AD52A1BEA6773E3A6C4D0B5DE6A77D05
     * status : 0000
     * timeStamp : 1587892395
     */

    private String appId;
    private String mchKey;
    private String message;
    private String nonceStr;
    private String packageValue;
    private String partnerId;
    private String prepayId;
    private String sign;
    private String status;
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
