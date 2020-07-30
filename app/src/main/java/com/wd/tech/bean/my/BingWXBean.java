package com.wd.tech.bean.my;

/**
 * date:2020/5/9 20:21
 * author:王黎杨(Administrator)
 * function:绑定微信
 * */
public class BingWXBean {

    /**
     * message : 查询成功
     * bindStatus : 2
     * status : 0000
     */

    private String message;
    private int bindStatus;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(int bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
