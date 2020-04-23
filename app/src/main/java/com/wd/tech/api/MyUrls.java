package com.wd.tech.api;

public interface MyUrls {
    String BASE_WRAP_URL = "https://172.17.8.100/techApi/";//内网
    String BASE_OUTER_URL = "https://mobile.bwstudent.com/techApi/";//外网

    String REGISTER_URL = "user/v1/register";//注册  post请求 参数 phone nickName pwd（加密后的）
    String LOGIN_URL = "user/v1/login";//登录   post请求 参数 phone pwd（加密后的）
    //资讯
    String CONSULT_XBANNER = "information/v1/bannerShow";//xbanner get
    String CONSULT_RECOMMEND_LIST = "information/v1/infoRecommendList";//列表展示 GET 头参
    String CONSULT_INFO_DATAILS = "information/v1/findInformationDetails";//列表详情 get
    String CONSULT_FIND_PLATE = "information/v1/findAllInfoPlate";//频道选择 get
    String CONSULT_FIND_TITLE = "information/v1/findInformationByTitle";//根据标题模糊查询信息
    String CONSULT_FIND_SOURCE = "information/v1/findInformationBySource";//根据作者模糊查询信息
}
