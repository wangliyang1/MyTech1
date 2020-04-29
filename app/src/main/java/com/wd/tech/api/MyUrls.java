package com.wd.tech.api;

public interface MyUrls {
    String BASE_WRAP_URL = "https://172.17.8.100/techApi/";//内网
    String BASE_OUTER_URL = "https://mobile.bwstudent.com/techApi/";//外网

    String REGISTER_URL = "user/v1/register";//注册  post请求 参数 phone nickName pwd（加密后的）
    String LOGIN_URL = "user/v1/login";//登录   post请求 参数 phone pwd（加密后的）
    String LOGIN_WX_URL = "user/v1/weChatLogin";//微信登录 post
    String FIND_USER_INTEGRAL = "user/verify/v1/findUserIntegral";//查询用户积分
    String FIND_VIP_COMMODITY = "tool/v1/findVipCommodityList";//查询所有会员商品
    String BUY_VIP = "tool/verify/v1/buyVip";//查询所有会员商品
    String PAY_VIP = "tool/verify/v1/pay";//支付
    //资讯
    String CONSULT_XBANNER = "information/v1/bannerShow";//xbanner get
    String CONSULT_RECOMMEND_LIST = "information/v1/infoRecommendList";//列表展示 GET 头参
    String CONSULT_INFO_DATAILS = "information/v1/findInformationDetails";//列表详情 get
    String CONSULT_FIND_PLATE = "information/v1/findAllInfoPlate";//频道选择 get
    String CONSULT_FIND_TITLE = "information/v1/findInformationByTitle";//根据标题模糊查询信息
    String CONSULT_FIND_SOURCE = "information/v1/findInformationBySource";//根据作者模糊查询信息
    String CONSULT_FIND_COMMENT = "information/v1/findAllInfoCommentList";//查看所有评论
    String CONSULT_ADD_COMMENT = "information/verify/v1/addInfoComment";//用户发表评论
    String CONSULT_ADD_GREAT = "information/verify/v1/addGreatRecord";//用户点赞
    String CONSULT_CANCLE_GREAT = "information/verify/v1/cancelGreat";//用户取消点赞
    String CONSULT_ADD_COLLECTION = "user/verify/v1/addCollection";//用户收藏资讯
    String CONSULT_CANCLE_COLLECTION = "user/verify/v1/cancelCollection";//用户取消收藏资讯
    String CONSULT_PAY_INTEGRAL = "information/verify/v1/infoPayByIntegral";//积分兑换

    //社区
    //社区列表
    String BASE_COMMUNITYLIST="community/v1/findCommunityList";
    //点赞
    String BASE_COMMUNITY_ZAN="community/verify/v1/addCommunityGreat";
    //取消点赞
    String BASE_DELETE_ZAN="community/verify/v1/cancelCommunityGreat";
    //发表帖子
    String BASE_POST="community/verify/v1/releasePost";
    //社区用户评论列表
    String BASE_COMMUNI_PL="community/v1/findCommunityUserCommentList";
    //社区评论
    String BASE_FILM="community/verify/v1/addCommunityComment";
    //查询用户帖子
    String BASE_USER_COM="community/verify/v1/findUserPostById";

    //消息
    String Friend_Notice = "chat/verify/v1/findFriendNoticePageList" ;

}
