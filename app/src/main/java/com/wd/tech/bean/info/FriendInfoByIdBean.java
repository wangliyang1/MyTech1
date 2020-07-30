package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/5 0005
 * author:胡锦涛(Administrator)
 * function:根据好友Id查询好友信息
 */
public class FriendInfoByIdBean {

    /**
     * result : {"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png","integral":70,"myGroupList":[{"blackFlag":0,"groupId":1245,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"开发者","hxGroupId":"43564156","role":3}],"nickName":"小猪猪！","phone":"15254221062","sex":1,"userId":1374,"userName":"2QQJXB15254221062","whetherFaceId":1,"whetherVip":2}
     * message : 查询成功
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
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png
         * integral : 70
         * myGroupList : [{"blackFlag":0,"groupId":1245,"groupImage":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","groupName":"开发者","hxGroupId":"43564156","role":3}]
         * nickName : 小猪猪！
         * phone : 15254221062
         * sex : 1
         * userId : 1374
         * userName : 2QQJXB15254221062
         * whetherFaceId : 1
         * whetherVip : 2
         */

        private String headPic;
        private int integral;
        private String nickName;
        private String phone;
        private int sex;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;
        private List<MyGroupListBean> myGroupList;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public List<MyGroupListBean> getMyGroupList() {
            return myGroupList;
        }

        public void setMyGroupList(List<MyGroupListBean> myGroupList) {
            this.myGroupList = myGroupList;
        }

        public static class MyGroupListBean {
            /**
             * blackFlag : 0
             * groupId : 1245
             * groupImage : http://mobile.bwstudent.com/images/tech/default/tech.jpg
             * groupName : 开发者
             * hxGroupId : 43564156
             * role : 3
             */

            private int blackFlag;
            private int groupId;
            private String groupImage;
            private String groupName;
            private String hxGroupId;
            private int role;

            public int getBlackFlag() {
                return blackFlag;
            }

            public void setBlackFlag(int blackFlag) {
                this.blackFlag = blackFlag;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public String getGroupImage() {
                return groupImage;
            }

            public void setGroupImage(String groupImage) {
                this.groupImage = groupImage;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getHxGroupId() {
                return hxGroupId;
            }

            public void setHxGroupId(String hxGroupId) {
                this.hxGroupId = hxGroupId;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }
        }
    }
}
