package com.wd.tech.bean.my;

import java.util.List;

/**
 * date:2020/5/9 20:56
 * author:王黎杨(Administrator)
 * function:用户积分明细
 * */
public class IntegralRecordBean {

    /**
     * result : [{"amount":20,"createTime":1589016630000,"direction":1,"type":9},{"amount":10,"createTime":1589008446000,"direction":1,"type":2},{"amount":20,"createTime":1588924920000,"direction":1,"type":1},{"amount":10,"createTime":1588842833000,"direction":1,"type":1},{"amount":20,"createTime":1588842800000,"direction":1,"type":9}]
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
         * amount : 20
         * createTime : 1589016630000
         * direction : 1
         * type : 9
         */

        private int amount;
        private long createTime;
        private int direction;
        private int type;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
