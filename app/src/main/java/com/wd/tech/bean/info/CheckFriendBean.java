package com.wd.tech.bean.info;

/**
 * date:2020/5/8 0008
 * author:胡锦涛(Administrator)
 * function:是否是好友
 */
public class CheckFriendBean {

    /**
     * flag : 1
     * message : 已是好友
     * status : 0000
     */

    private int flag;
    private String message;
    private String status;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
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
