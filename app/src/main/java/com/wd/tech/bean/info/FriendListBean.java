package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/4/22 0022
 * author:胡锦涛(Administrator)
 * function:
 */
public class FriendListBean {

    /**
     * result : [{"friendUid":1374,"headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","nickName":"小猪猪！","remarkName":"小猪猪！","userName":"2QQJXB15254221062","vipFlag":2}]
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
         * friendUid : 1374
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : 小猪猪！
         * remarkName : 小猪猪！
         * userName : 2QQJXB15254221062
         * vipFlag : 2
         */

        private int friendUid;
        private String headPic;
        private String nickName;
        private String remarkName;
        private String userName;
        private int vipFlag;

        public int getFriendUid() {
            return friendUid;
        }

        public void setFriendUid(int friendUid) {
            this.friendUid = friendUid;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getVipFlag() {
            return vipFlag;
        }

        public void setVipFlag(int vipFlag) {
            this.vipFlag = vipFlag;
        }
    }
}
