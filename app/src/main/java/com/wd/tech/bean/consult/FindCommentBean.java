package com.wd.tech.bean.consult;

import java.util.List;

/**
 * date:2020/4/23 15:40
 * author:王黎杨(Administrator)
 * function:所有评论列表
 * */
public class FindCommentBean {

    /**
     * result : [{"commentTime":1558165355000,"content":"没有6","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-05-21/20190521162713.png","id":2331,"infoId":3,"nickName":"珍惜","userId":340},{"commentTime":1555753714000,"content":"行吧","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-23/20190423111759.jpg","id":127,"infoId":3,"nickName":"破碎","userId":10},{"commentTime":1555663319000,"content":"硅谷大街上随便找个地方了吗？？？","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2019-04-27/20190427164039.jpg","id":65,"infoId":3,"nickName":"UFO","userId":61}]
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
         * commentTime : 1558165355000
         * content : 没有6
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2019-05-21/20190521162713.png
         * id : 2331
         * infoId : 3
         * nickName : 珍惜
         * userId : 340
         */

        private long commentTime;
        private String content;
        private String headPic;
        private int id;
        private int infoId;
        private String nickName;
        private int userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
