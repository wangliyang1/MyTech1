package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/4/21 0021
 * author:胡锦涛(Administrator)
 * function:用户好友通知
 */
public class FriendNoticeBean {

    /**
     * result : [{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","fromNickName":"大前门","fromUid":1377,"noticeId":1068,"noticeTime":1587279566000,"receiveUid":1372,"remark":"","status":1},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417210315.jpg","fromNickName":"人间惊鸿客","fromUid":1385,"noticeId":1063,"noticeTime":1587131652000,"receiveUid":1372,"remark":"我是人间惊鸿客","status":2},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg","fromNickName":"黎明将之","fromUid":1381,"noticeId":1058,"noticeTime":1587128276000,"receiveUid":1372,"remark":"我是。。","status":2},{"fromHeadPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png","fromNickName":"小猪猪！","fromUid":1374,"noticeId":1057,"noticeTime":1587094215000,"receiveUid":1372,"remark":"","status":2}]
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
         * fromHeadPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * fromNickName : 大前门
         * fromUid : 1377
         * noticeId : 1068
         * noticeTime : 1587279566000
         * receiveUid : 1372
         * remark :
         * status : 1
         */

        private String fromHeadPic;
        private String fromNickName;
        private int fromUid;
        private int noticeId;
        private long noticeTime;
        private int receiveUid;
        private String remark;
        private int status;

        public String getFromHeadPic() {
            return fromHeadPic;
        }

        public void setFromHeadPic(String fromHeadPic) {
            this.fromHeadPic = fromHeadPic;
        }

        public String getFromNickName() {
            return fromNickName;
        }

        public void setFromNickName(String fromNickName) {
            this.fromNickName = fromNickName;
        }

        public int getFromUid() {
            return fromUid;
        }

        public void setFromUid(int fromUid) {
            this.fromUid = fromUid;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(long noticeTime) {
            this.noticeTime = noticeTime;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
