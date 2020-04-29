package com.wd.tech.bean.community;

import java.util.List;

/**
 * date:2020/4/23 0023
 * author:王黎杨
 * function:社区列表
 */
public class CommunityListBean {

    /**
     * result : [{"comment":1,"communityCommentVoList":[{"content":"nihao a ","nickName":"诗和远方","userId":1372}],"content":"这是我发送的帖子","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-26/2634120200426095501.jpg","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-25/20200425144642.png","id":1946,"nickName":"TestCode","praise":0,"publishTime":1587866101000,"userId":1425,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":5,"communityCommentVoList":[{"content":"5","nickName":"诗和远方","userId":1372},{"content":"4","nickName":"诗和远方","userId":1372},{"content":"3","nickName":"诗和远方","userId":1372}],"content":"2","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-26/1532420200426090648.jpg","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1945,"nickName":"诗和远方","praise":0,"publishTime":1587863208000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"1","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1944,"nickName":"诗和远方","praise":0,"publishTime":1587862747000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"加载图片2","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1943,"nickName":"诗和远方","praise":0,"publishTime":1587862068000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"加载图片","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1942,"nickName":"诗和远方","praise":0,"publishTime":1587861134000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"\"终于...\"","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1941,"nickName":"诗和远方","praise":9,"publishTime":1587821305000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"\"你好啊\"","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1940,"nickName":"诗和远方","praise":1,"publishTime":1587820828000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"\"改善测试\"","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1939,"nickName":"诗和远方","praise":2,"publishTime":1587819609000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":0,"communityCommentVoList":[],"content":"\"再次发帖\"","file":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","id":1938,"nickName":"诗和远方","praise":1,"publishTime":1587819079000,"signature":"剑与远征","userId":1372,"whetherFollow":2,"whetherGreat":2,"whetherVip":2},{"comment":1,"communityCommentVoList":[{"content":"1","nickName":"小小科技","userId":1384}],"content":"666","file":"http://mobile.bwstudent.com/images/tech/community_pic/2020-04-25/2657320200425203727.png","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":1937,"nickName":"小小科技","praise":1,"publishTime":1587818247000,"signature":"666","userId":1384,"whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
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
         * comment : 1
         * communityCommentVoList : [{"content":"nihao a ","nickName":"诗和远方","userId":1372}]
         * content : 这是我发送的帖子
         * file : http://mobile.bwstudent.com/images/tech/community_pic/2020-04-26/2634120200426095501.jpg
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-25/20200425144642.png
         * id : 1946
         * nickName : TestCode
         * praise : 0
         * publishTime : 1587866101000
         * userId : 1425
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         * signature : 剑与远征
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private int userId;
        private int whetherFollow;
        private int whetherGreat;
        private int whetherVip;
        private String signature;
        private List<CommunityCommentVoListBean> communityCommentVoList;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public List<CommunityCommentVoListBean> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<CommunityCommentVoListBean> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }

        public static class CommunityCommentVoListBean {
            /**
             * content : nihao a
             * nickName : 诗和远方
             * userId : 1372
             */

            private String content;
            private String nickName;
            private int userId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
}
