package com.wd.tech.bean.info;

import java.util.List;

/**
 * date:2020/5/5 0005
 * author:胡锦涛(Administrator)
 * function:好友聊天记录
 */
public class ChatMsgBean {

    /**
     * result : [{"chatTime":1588670005000,"content":"Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=","nickName":"小猪猪！"},{"chatTime":1588669677000,"content":"Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=","nickName":"诗和远方"},{"chatTime":1588669140000,"nickName":"诗和远方"},{"chatTime":1588669094000,"nickName":"诗和远方"},{"chatTime":1588668959000,"nickName":"诗和远方"},{"chatTime":1587467356000,"nickName":"诗和远方","picUrl":""},{"chatTime":1587465453000,"nickName":"小猪猪！","picUrl":""}]
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
         * chatTime : 1588670005000
         * content : Db4txQCaRIAsZDBbL0QAUxFL8u3PnPyOLa8bTHy7Q3lQgtXVVxyjo/NvDMb3iY37H9NvuJanJGuySKdLLmXg2ViqDlF4LO+S7iHV4DeuWim72SwX2voI4vgQD7evGpnRVGt5vHbp2oqNzCKQnDQNeuLOT8e2B8rHhtxOh7wdQEU=
         * nickName : 小猪猪！
         * picUrl :
         */

        private long chatTime;
        private String content;
        private String nickName;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }
}
