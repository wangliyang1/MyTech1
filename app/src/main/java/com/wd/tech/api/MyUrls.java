package com.wd.tech.api;

public interface MyUrls {
    String BASE_WRAP_URL = "https://172.17.8.100/techApi/";//内网
    String BASE_OUTER_URL = "https://mobile.bwstudent.com/techApi/";//外网

    String REGISTER_URL = "user/v1/register";//注册  post请求 参数 phone nickName pwd（加密后的）
    String LOGIN_URL = "user/v1/login";//登录   post请求 参数 phone pwd（加密后的）
}
