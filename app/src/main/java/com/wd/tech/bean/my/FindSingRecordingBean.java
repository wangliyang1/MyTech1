package com.wd.tech.bean.my;

import java.io.Serializable;
import java.util.List;

/**
 * date:2020/5/9 15:46
 * author:王黎杨(Administrator)
 * function:查询当月签到日期
 */
public class FindSingRecordingBean implements Serializable {

    /**
     * result : ["2020-05-07","2020-05-08","2020-05-09"]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<String> result;

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
