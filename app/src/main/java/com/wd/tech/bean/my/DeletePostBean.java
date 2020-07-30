package com.wd.tech.bean.my;

/**
 * date:2020/5/5 17:28
 * author:王黎杨(Administrator)
 * function:删除帖子 */
public class DeletePostBean {

    /**
     * message : 删除成功
     * status : 1001
     */

    private String message;
    private String status;

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
