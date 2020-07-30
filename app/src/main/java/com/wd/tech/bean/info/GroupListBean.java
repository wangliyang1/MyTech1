package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/7 0007
 * author:胡锦涛(Administrator)
 * function:查询我加入的所有群
 */
public class GroupListBean {

    /**
     * result : [{"blackFlag":0,"groupId":1228,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"我曾向往星空","hxGroupId":"43462109","role":3}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * blackFlag : 0
         * groupId : 1228
         * groupImage : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * groupName : 我曾向往星空
         * hxGroupId : 43462109
         * role : 3
         */

        private int blackFlag;
        private int groupId;
        private String groupImage;
        private String groupName;
        private String hxGroupId;
        private int role;

        public int getBlackFlag() {
            return blackFlag;
        }

        public void setBlackFlag(int blackFlag) {
            this.blackFlag = blackFlag;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupImage() {
            return groupImage;
        }

        public void setGroupImage(String groupImage) {
            this.groupImage = groupImage;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getHxGroupId() {
            return hxGroupId;
        }

        public void setHxGroupId(String hxGroupId) {
            this.hxGroupId = hxGroupId;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }
    }
}
