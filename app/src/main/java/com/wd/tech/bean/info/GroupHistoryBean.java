package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/7 0007
 * author:胡锦涛(Administrator)
 * function:群聊天记录
 */
public class GroupHistoryBean {

    /**
     * result : [{"chatContent":"Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","nickName":"诗和远方","userId":1372},{"chatImage":"","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","nickName":"诗和远方","userId":1372}]
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
         * chatContent : Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png
         * nickName : 诗和远方
         * userId : 1372
         * chatImage :
         */

        private String chatContent;
        private String headPic;
        private String nickName;
        private int userId;
        private String chatImage;

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getChatImage() {
            return chatImage;
        }

        public void setChatImage(String chatImage) {
            this.chatImage = chatImage;
        }
    }
}
