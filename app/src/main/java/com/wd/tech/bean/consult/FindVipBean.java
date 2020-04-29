package com.wd.tech.bean.consult;

import java.util.List;

/**
 * date:2020/4/25 14:56
 * author:王黎杨(Administrator)
 * function:会员购买
 * */
public class FindVipBean {

    /**
     * result : [{"commodityId":1001,"commodityName":"会员周卡","imageUrl":"http://mobile.bwstudent.com/images/tech/community_pic/vip_zk.jpg","price":0.01},{"commodityId":1002,"commodityName":"会员月卡","imageUrl":"http://mobile.bwstudent.com/images/tech/community_pic/vip_zk.jpg","price":0.03},{"commodityId":1003,"commodityName":"会员季卡","imageUrl":"http://mobile.bwstudent.com/images/tech/community_pic/vip_zk.jpg","price":0.05},{"commodityId":1004,"commodityName":"会员年卡","imageUrl":"http://mobile.bwstudent.com/images/tech/community_pic/vip_zk.jpg","price":0.08}]
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
         * commodityId : 1001
         * commodityName : 会员周卡
         * imageUrl : http://mobile.bwstudent.com/images/tech/community_pic/vip_zk.jpg
         * price : 0.01
         */

        private int commodityId;
        private String commodityName;
        private String imageUrl;
        private double price;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
