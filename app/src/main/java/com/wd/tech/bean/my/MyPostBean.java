package com.wd.tech.bean.my;

import java.util.List;

/**
 * date:2020/5/5 16:27
 * author:王黎杨(Administrator)
 * function:我的帖子 */
public class MyPostBean {

    /**
     * result : [{"comment":0,"content":"Fragment是Activity界面中的一部分，可以简单的理解为模块化的Activity，它是从Android 3.0后引入的，在3.0版本之前使用需导入android-support-v4.jar兼容包","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg","id":2045,"nickName":"黎明将之","praise":0,"publishTime":1588667158000,"signature":"不能哭，眼泪会被冻住的！！","userId":1381}]
     * message : 查詢成功
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
         * comment : 0
         * content : Fragment是Activity界面中的一部分，可以简单的理解为模块化的Activity，它是从Android 3.0后引入的，在3.0版本之前使用需导入android-support-v4.jar兼容包
         * file :
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg
         * id : 2045
         * nickName : 黎明将之
         * praise : 0
         * publishTime : 1588667158000
         * signature : 不能哭，眼泪会被冻住的！！
         * userId : 1381
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private String signature;
        private int userId;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
