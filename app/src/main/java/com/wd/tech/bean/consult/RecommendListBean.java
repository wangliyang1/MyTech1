package com.wd.tech.bean.consult;

import java.util.List;
/*
* 资讯列表实体类
* */
public class RecommendListBean {

    /**
     * result : [{"collection":5,"id":68,"releaseTime":1573023083000,"share":0,"source":"ITBEAR科技资讯","summary":"11月6日消息，今日在第二届中国国际进口博览会上，诺基亚与中国三大运营商分别签署2020年合作框架协议，总价值157亿元人民币。","thumbnail":"http://www.itbear.com.cn/upload/2019-11/191106170713291.jpg","title":"诺基亚与三大运营商分别签约，总价值157亿元","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":4,"id":67,"releaseTime":1572936683000,"share":0,"source":"ITBEAR科技资讯","summary":"最近，百度李彦宏曾多次演示的小度人工智能音箱入驻了杭州市萧山区的一家盲人推拿店，给盲人按摩师带来了意想不到的帮助。","thumbnail":"http://www.itbear.com.cn/upload/2019-11/191106194329661.jpg","title":"这次是杭州！快来看李彦宏的\u201cAI助盲\u201d路线图","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":5,"id":66,"releaseTime":1572850283000,"share":0,"source":"ITBEAR科技资讯","summary":"区块链技术正越来越广泛地应用到人民生活的各个方面。近期，国家互联网应急中心对国家电子合同备案平台进行了升级，正式上线区块链存证功能，并部署了多个监督节点。而据最新消息，迅雷以领先的区块链技术优势正式成为国家电子合同备案平台区块链监督网络的最新节点，也是继阿里、奇安信(原360企业安全)之后的第三个企业类节点。","thumbnail":"http://www.itbear.com.cn/upload/2019-09/190926152255071.png","title":"国家互联网应急中心联手迅雷 推动契约生态区块链化","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":4,"id":65,"releaseTime":1572763883000,"share":2,"source":"ITBEAR科技资讯","summary":"双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。","thumbnail":"http://www.itbear.com.cn/upload/2019-11/191106140480852.png","title":"2019年Q3智能音箱数据发布，天猫精灵再次国内第一","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":0,"infoAdvertisingVo":{"content":"八维教育","id":1,"pic":"http://mobile.bwstudent.com/images/tech/ad/bw.png","url":"http://www.bwie.com"},"share":0,"whetherAdvertising":1,"whetherCollection":0,"whetherPay":0}]
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
         * collection : 5
         * id : 68
         * releaseTime : 1573023083000
         * share : 0
         * source : ITBEAR科技资讯
         * summary : 11月6日消息，今日在第二届中国国际进口博览会上，诺基亚与中国三大运营商分别签署2020年合作框架协议，总价值157亿元人民币。
         * thumbnail : http://www.itbear.com.cn/upload/2019-11/191106170713291.jpg
         * title : 诺基亚与三大运营商分别签约，总价值157亿元
         * whetherAdvertising : 2
         * whetherCollection : 2
         * whetherPay : 2
         * infoAdvertisingVo : {"content":"八维教育","id":1,"pic":"http://mobile.bwstudent.com/images/tech/ad/bw.png","url":"http://www.bwie.com"}
         */

        private int collection;
        private int id;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherAdvertising;
        private int whetherCollection;
        private int whetherPay;
        private InfoAdvertisingVoBean infoAdvertisingVo;

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

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

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherAdvertising() {
            return whetherAdvertising;
        }

        public void setWhetherAdvertising(int whetherAdvertising) {
            this.whetherAdvertising = whetherAdvertising;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherPay() {
            return whetherPay;
        }

        public void setWhetherPay(int whetherPay) {
            this.whetherPay = whetherPay;
        }

        public InfoAdvertisingVoBean getInfoAdvertisingVo() {
            return infoAdvertisingVo;
        }

        public void setInfoAdvertisingVo(InfoAdvertisingVoBean infoAdvertisingVo) {
            this.infoAdvertisingVo = infoAdvertisingVo;
        }

        public static class InfoAdvertisingVoBean {
            /**
             * content : 八维教育
             * id : 1
             * pic : http://mobile.bwstudent.com/images/tech/ad/bw.png
             * url : http://www.bwie.com
             */

            private String content;
            private int id;
            private String pic;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
