package com.wd.tech.bean.info;

/**
 * date:2020/5/6 0006
 * author:胡锦涛(Administrator)
 * function:创建群聊
 */
public class CreateGroupBean {

    /**
     * groupId : 1228
     * message : 创建成功
     * status : 0000
     */

    private int groupId;
    private String message;
    private String status;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
