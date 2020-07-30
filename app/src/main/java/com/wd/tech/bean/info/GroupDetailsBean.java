package com.wd.tech.bean.info;

import java.io.Serializable;

/**
 * date:2020/5/8 0008
 * author:胡锦涛(Administrator)
 * function:群详情
 */
public class GroupDetailsBean{

    /**
     * result : {"currentCount":1,"description":"","groupId":1228,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"我曾向往星空","hxGroupId":"43462109","maxCount":10,"ownerUid":1372}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {
        /**
         * currentCount : 1
         * description :
         * groupId : 1228
         * groupImage : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * groupName : 我曾向往星空
         * hxGroupId : 43462109
         * maxCount : 10
         * ownerUid : 1372
         */

        private int currentCount;
        private String description;
        private int groupId;
        private String groupImage;
        private String groupName;
        private String hxGroupId;
        private int maxCount;
        private int ownerUid;

        public int getCurrentCount() {
            return currentCount;
        }

        public void setCurrentCount(int currentCount) {
            this.currentCount = currentCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public int getOwnerUid() {
            return ownerUid;
        }

        public void setOwnerUid(int ownerUid) {
            this.ownerUid = ownerUid;
        }
    }
}
