package com.wd.tech.bean.consult;

import java.util.List;

/**
 * date:2020/4/22 17:07
 * author:王黎杨(Administrator)
 * function:根据标题模糊查询文章
 */
public class FindByTitleBean {

    /**
     * result : [{"id":65,"releaseTime":1572763883000,"source":"ITBEAR科技资讯","title":"2019年Q3智能音箱数据发布，天猫精灵再次国内第一"},{"id":58,"releaseTime":1553063178000,"source":"中原网","title":"5G元年话5G：让生活再快一些"},{"id":57,"releaseTime":1553062827000,"source":"华为","title":"华为：5G基站部署要像搭积木一样便捷，AI要无处不在"},{"id":53,"releaseTime":1539585103000,"source":"高街高参","title":"马云力挺杭州\u201c数字经济\u201d第一城之后，我先咽下这口泡沫"},{"id":39,"releaseTime":1539400673000,"source":"看航空","title":"无人驾驶航班离成为现实更近了一步"}]
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
         * id : 65
         * releaseTime : 1572763883000
         * source : ITBEAR科技资讯
         * title : 2019年Q3智能音箱数据发布，天猫精灵再次国内第一
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
