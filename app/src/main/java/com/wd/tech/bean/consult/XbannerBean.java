package com.wd.tech.bean.consult;

import com.stx.xhb.androidx.entity.SimpleBannerInfo;

import java.util.List;
import java.util.SimpleTimeZone;

public class XbannerBean {

    /**
     * result : [{"imageUrl":"http://mobile.bwstudent.com/images/tech/banner/191030095025561.png","jumpUrl":"wd://information?infoId=64","rank":1,"title":"区块链\u201c地位\u201d提升，百度智能云助力国家加快推动区块链产业自主创新"},{"imageUrl":"http://mobile.bwstudent.com/images/tech/banner/191106170557911.png","jumpUrl":"http://www.itbear.com.cn/html/2019-11/362293.html","rank":2,"title":"1亿像素能装下多少字？小米CC9 Pro挑战拍《新华字典》"},{"imageUrl":"http://mobile.bwstudent.com/images/tech/banner/191106165919191.jpg","jumpUrl":"http://www.itbear.com.cn/html/2019-11/362292.html","rank":3,"title":"科大讯飞翻译机强势赋能进博会 AI国货让世界\u201c聊得来\u201d"},{"imageUrl":"http://mobile.bwstudent.com/images/tech/banner/191106165563551.jpg","jumpUrl":"http://www.itbear.com.cn/html/2019-11/362290.html","rank":4,"title":"揭秘5G商用套路：看看北京的5G网络到底如何？"},{"imageUrl":"http://mobile.bwstudent.com/images/tech/banner/191106154094941.png","jumpUrl":"http://www.itbear.com.cn/html/2019-11/362266.html","rank":5,"title":"中国市场这么大，送餐机器人助力4万亿餐饮业转型"},{"imageUrl":"http://mobile.bwstudent.com/images/tech/banner/20181026151647.png","jumpUrl":"http://mobile.bwstudent.com/htm/lottery/index.html","rank":5,"title":"抽奖"}]
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

    public static class ResultBean extends SimpleBannerInfo {
        /**
         * imageUrl : http://mobile.bwstudent.com/images/tech/banner/191030095025561.png
         * jumpUrl : wd://information?infoId=64
         * rank : 1
         * title : 区块链“地位”提升，百度智能云助力国家加快推动区块链产业自主创新
         */

        private String imageUrl;
        private String jumpUrl;
        private int rank;
        private String title;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }
}
