package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/6 0006
 * author:胡锦涛(Administrator)
 * function:查询好友对话
 */
public class ChatBean {

    /**
     * result : [{"chatTime":1588720550000,"content":"Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=","direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","userId":1372},{"chatTime":1588670005000,"content":"Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=","direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png","userId":1374},{"chatTime":1588669677000,"content":"Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=","direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","userId":1372},{"chatTime":1588669140000,"direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","userId":1372},{"chatTime":1588669094000,"direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","userId":1372},{"chatTime":1588668959000,"direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","userId":1372},{"chatTime":1587467356000,"direction":1,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","picUrl":"","userId":1372},{"chatTime":1587465453000,"direction":2,"headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417213635.png","picUrl":"","userId":1374}]
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
         * chatTime : 1588720550000
         * content : Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=
         * direction : 1
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png
         * userId : 1372
         * picUrl :
         */

        private long chatTime;
        private String content;
        private int direction;
        private String headPic;
        private int userId;
        private String picUrl;

        public long getChatTime() {
            return chatTime;
        }

        public void setChatTime(long chatTime) {
            this.chatTime = chatTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }
}
