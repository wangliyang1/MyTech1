package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/4/22 0022
 * author:胡锦涛(Administrator)
 * function:用户所有分组
 */
public class FriendGroupBean {

    /**
     * result : [{"black":1,"currentNumber":1,"customize":1,"groupId":2942,"groupName":"我的好友"},{"black":2,"currentNumber":0,"customize":1,"groupId":2943,"groupName":"黑名单"},{"black":1,"currentNumber":0,"customize":2,"groupId":2980,"groupName":"乌鸦"}]
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
         * black : 1
         * currentNumber : 1
         * customize : 1
         * groupId : 2942
         * groupName : 我的好友
         */

        private int black;
        private int currentNumber;
        private int customize;
        private int groupId;
        private String groupName;
        private boolean isClosed;

        public boolean isClosed() {
            return isClosed;
        }

        public void setClosed(boolean closed) {
            isClosed = closed;
        }

        public int getBlack() {
            return black;
        }

        public void setBlack(int black) {
            this.black = black;
        }

        public int getCurrentNumber() {
            return currentNumber;
        }

        public void setCurrentNumber(int currentNumber) {
            this.currentNumber = currentNumber;
        }

        public int getCustomize() {
            return customize;
        }

        public void setCustomize(int customize) {
            this.customize = customize;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }

}
