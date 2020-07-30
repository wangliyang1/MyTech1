package com.wd.tech.bean.my;

import java.util.List;

/**
 * Time:2020/5/5 0005下午 4:36202005
 * 邮箱:2094158527@qq.com
 * 作者:王黎杨
 * 类功能:
 */
public class TongZhiBean {
    /**
     * result : [{"content":"小猪猪！同意了您的好友申请","createTime":1588059066000,"id":1155,"receiveUid":1385},{"content":"猫氿同意了您的好友申请","createTime":1587297709000,"id":1132,"receiveUid":1385},{"content":"诗和远方同意了您的好友申请","createTime":1587131783000,"id":1126,"receiveUid":1385}]
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
         * content : 小猪猪！同意了您的好友申请
         * createTime : 1588059066000
         * id : 1155
         * receiveUid : 1385
         */

        private String content;
        private long createTime;
        private int id;
        private int receiveUid;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }
    }
}
