package com.wd.tech.bean;
/*
* 登录bean类
* */
public class LoginBean {

    /**
     * result : {"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg","nickName":"黎明将之","phone":"18531029738","pwd":"cleasvfpN/Xy4m3wwGYxdiF2B06eIYHBaPJuZZcttJeKX4wYOd4f/Jg7ZOSCZ5KyRS11Vn3cRoUzC8V1E8tasoqyOUM2MV2p9EnwcJD+53LRYCkMSHqKyupBKDAJK8X6YdxEfnJw2vmpZjxLrSxoHGc0CpCRfpKaCH7iFu7P38U=","sessionId":"15873017657861381","signature":"不能哭，眼泪会被冻住的！！","userId":1381,"userName":"PdsOa718531029738","whetherFaceId":0,"whetherVip":2}
     * message : 登录成功
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
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417201518.jpg
         * nickName : 黎明将之
         * phone : 18531029738
         * pwd : cleasvfpN/Xy4m3wwGYxdiF2B06eIYHBaPJuZZcttJeKX4wYOd4f/Jg7ZOSCZ5KyRS11Vn3cRoUzC8V1E8tasoqyOUM2MV2p9EnwcJD+53LRYCkMSHqKyupBKDAJK8X6YdxEfnJw2vmpZjxLrSxoHGc0CpCRfpKaCH7iFu7P38U=
         * sessionId : 15873017657861381
         * signature : 不能哭，眼泪会被冻住的！！
         * userId : 1381
         * userName : PdsOa718531029738
         * whetherFaceId : 0
         * whetherVip : 2
         */

        private String headPic;
        private String nickName;
        private String phone;
        private String pwd;
        private String sessionId;
        private String signature;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWhetherFaceId() {
            return whetherFaceId;
        }

        public void setWhetherFaceId(int whetherFaceId) {
            this.whetherFaceId = whetherFaceId;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }
    }
}
