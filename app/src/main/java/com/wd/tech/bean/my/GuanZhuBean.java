package com.wd.tech.bean.my;

import java.util.List;

/**
 * Time:2020/4/29 0029下午 10:00202004
 * 邮箱:2094158527@qq.com
 * 作者:王黎杨
 * 类功能:
 */
public class GuanZhuBean {
    /**
     * result : [{"focusTime":1587129747000,"focusUid":1012,"headPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9q4IKgFVDMcblYbXSz6GUQoTwPOw8O5R0WHp7K5NYicnYOTKTFicse2sRhicJiaibOFGmviaic69jzXiaEA/132","nickName":"崇_7Wa","signature":"手机等你拿到南宁等你","userId":1385,"whetherMutualFollow":2,"whetherVip":2}]
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
         * focusTime : 1587129747000
         * focusUid : 1012
         * headPic : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo9q4IKgFVDMcblYbXSz6GUQoTwPOw8O5R0WHp7K5NYicnYOTKTFicse2sRhicJiaibOFGmviaic69jzXiaEA/132
         * nickName : 崇_7Wa
         * signature : 手机等你拿到南宁等你
         * userId : 1385
         * whetherMutualFollow : 2
         * whetherVip : 2
         */

        private long focusTime;
        private int focusUid;
        private String headPic;
        private String nickName;
        private String signature;
        private int userId;
        private int whetherMutualFollow;
        private int whetherVip;

        public long getFocusTime() {
            return focusTime;
        }

        public void setFocusTime(long focusTime) {
            this.focusTime = focusTime;
        }

        public int getFocusUid() {
            return focusUid;
        }

        public void setFocusUid(int focusUid) {
            this.focusUid = focusUid;
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

        public int getWhetherMutualFollow() {
            return whetherMutualFollow;
        }

        public void setWhetherMutualFollow(int whetherMutualFollow) {
            this.whetherMutualFollow = whetherMutualFollow;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }
    }
}
