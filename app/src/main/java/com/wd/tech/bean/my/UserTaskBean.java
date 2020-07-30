package com.wd.tech.bean.my;

import java.util.List;

/**
 * date:2020/5/6 17:00
 * author:王黎杨(Administrator)
 * function:用户任务列表 */
public class UserTaskBean {

    /**
     * result : [{"status":2,"taskId":1001,"taskIntegral":10,"taskName":"每日签到","taskType":1},{"status":2,"taskId":1002,"taskIntegral":10,"taskName":"每日首评","taskType":1},{"status":1,"taskId":1003,"taskIntegral":20,"taskName":"每日发帖","taskType":1},{"status":2,"taskId":1004,"taskIntegral":20,"taskName":"每日分享","taskType":1},{"status":1,"taskId":1005,"taskIntegral":20,"taskName":"每日查看广告","taskType":1},{"status":2,"taskId":1006,"taskIntegral":50,"taskName":"完善用户信息","taskType":2},{"status":2,"taskId":1007,"taskIntegral":50,"taskName":"绑定微信","taskType":2}]
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
         * status : 2
         * taskId : 1001
         * taskIntegral : 10
         * taskName : 每日签到
         * taskType : 1
         */

        private int status;
        private int taskId;
        private int taskIntegral;
        private String taskName;
        private int taskType;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getTaskIntegral() {
            return taskIntegral;
        }

        public void setTaskIntegral(int taskIntegral) {
            this.taskIntegral = taskIntegral;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }
    }
}
