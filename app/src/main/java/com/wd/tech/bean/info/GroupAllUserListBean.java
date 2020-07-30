package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/10 0010
 * author:胡锦涛(Administrator)
 * function:群组所有用户
 */
public class GroupAllUserListBean {

    /**
     * result : [{"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","nickName":"诗和远方","role":3,"userId":1372},{"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png","nickName":"小猪猪！","role":1,"userId":1374}]
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
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png
         * nickName : 诗和远方
         * role : 3
         * userId : 1372
         */

        private String headPic;
        private String nickName;
        private int role;
        private int userId;

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

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
