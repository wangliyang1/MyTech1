package com.wd.tech.bean;

/**
 * date:2020/4/26 17:14
 * author:王黎杨(Administrator)
 * function:支付宝
 * */
public class PayZFBean {

    /**
     * result : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018081661068647&biz_content=%7B%22out_trade_no%22%3A%2220200426170942997%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.03%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiProd%2FaliPay%2FtechNotification&sign=Y9wFuaOLNWf7Fsevh%2BukipeHkByYcRJjo1%2B%2B7ct9wiXGfqxo5Y%2BZ%2FC%2BWg39vvUZUDNyfbNTBJvf04QHOD7CNW5zyX2ROItl4aQ%2BZuk4MYnoUVZD1DLh6L92ONLaDXs9kbEcAK8qBxEFPzL0bVFzrrFZ%2F%2FzKwVH%2FoPpmie3aDiKoejg%2B%2FOsbTDyIfHnMmgH0Q5gOC8hSmVYc9bU3lT2R4TaaeSi905lv2DrX9szfzpOj%2BiS5ZtcY6tSM7WatKrks0mie1OaSc9sEW7V2BF9beBdgEHUo7jJlZvi6VLdZjSDjhi%2BYQ6%2Fv%2FWn0DtjuG0hnEhWxVEZW1aHPGqsT%2FmGCsMg%3D%3D&sign_type=RSA2&timestamp=2020-04-26+17%3A14%3A07&version=1.0
     * message : ok
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
