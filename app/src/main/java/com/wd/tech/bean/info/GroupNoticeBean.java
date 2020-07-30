package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/8 0008
 * author:胡锦涛(Administrator)
 * function:群组通知
 * */
public class GroupNoticeBean {

    /**
     * result : [{"groupName":"我曾向往星空","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png","nickName":"小猪猪！","noticeId":665,"noticeTime":1588926552000,"remark":"小猪猪","status":1,"type":2}]
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
         * groupName : 我曾向往星空
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png
         * nickName : 小猪猪！
         * noticeId : 665
         * noticeTime : 1588926552000
         * remark : 小猪猪
         * status : 1
         * type : 2
         */

        private String groupName;
        private String headPic;
        private String nickName;
        private int noticeId;
        private long noticeTime;
        private String remark;
        private int status;
        private int type;

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
