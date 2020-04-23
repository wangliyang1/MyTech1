package com.wd.tech.bean.consult;

import java.util.List;

/**
 * date:2020/4/22 16:02
 * author:王黎杨(Administrator)
 * function:资讯频道模块
 */
public class FindPlaterBean {

    /**
     * result : [{"id":1,"name":"电商消费","pic":"http://mobile.bwstudent.com/images/tech/plate/dsxf.png"},{"id":2,"name":"区块链","pic":"http://mobile.bwstudent.com/images/tech/plate/qkl.png"},{"id":3,"name":"AI世界","pic":"http://mobile.bwstudent.com/images/tech/plate/AIsj.png"},{"id":4,"name":"人工智能","pic":"http://mobile.bwstudent.com/images/tech/plate/rgzn.png"},{"id":5,"name":"车与出行","pic":"http://mobile.bwstudent.com/images/tech/plate/cycx.png"},{"id":6,"name":"智能终端","pic":"http://mobile.bwstudent.com/images/tech/plate/znzd.png"},{"id":7,"name":"金融地产","pic":"http://mobile.bwstudent.com/images/tech/plate/jrdc.png"},{"id":8,"name":"大数据","pic":"http://mobile.bwstudent.com/images/tech/plate/dsj.png"},{"id":9,"name":"社交通讯","pic":"http://mobile.bwstudent.com/images/tech/plate/sjtx.png"},{"id":10,"name":"全球热点","pic":"http://mobile.bwstudent.com/images/tech/plate/qqrd.png"}]
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
         * id : 1
         * name : 电商消费
         * pic : http://mobile.bwstudent.com/images/tech/plate/dsxf.png
         */

        private int id;
        private String name;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
