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
    //查询好友通知
    String BASE_FRIEND_NOTICE="chat/verify/v1/findFriendNoticePageList";
    //查询分组
    String BASE_FIND_ALLGROUP="chat/verify/v1/findFriendGroupList";
    //查询分组下所有好友信息
    String BASE_FINDMAN_BYGROUP="chat/verify/v1/findFriendListByGroupId";
    //查询我的好友列表 get searchName
    String BASE_FIND_FRIEND="chat/verify/v1/searchFriend";
    //删除好友聊天记录
    String BASE_DELETE_FRIENDINFO="chat/verify/v1/deleteChatRecord";
    //根据手机号
    String BASE_SEUSER_BYPHONE="user/verify/v1/findUserByPhone";
    //查询好友信息
    String BASE_FRIENDINFO_ID="user/verify/v1/queryFriendInformation";
    //查询好友对话记录
    String BASE_CHAT="chat/verify/v1/findDialogueRecordPageList";
    //查询好友聊天记录
    String BASE_CHATHISTORY="chat/verify/v1/findChatRecordPageList";
    //发送消息
    String BASE_SEND_MSG="chat/verify/v1/sendMessage";
    //删除好友
    String BASE_DELETE_FRIEND="chat/verify/v1/deleteFriendRelation";
    //删除好友聊天记录
    String BASE_DELETE_HISTORY="chat/verify/v1/deleteChatRecord";
    //创建群聊
    String BASE_CREATE_GROUP="group/verify/v1/createGroup";
    //查询所有加入的群组
    String BASE_ALLGROUPS="group/verify/v1/findUserJoinedGroup";
    //查看群组聊天记录
    String BASE_GROUP_HISTORY="group/verify/v1/findGroupChatRecordPage";
    //查询群组详细信息
    String BASE_GROUP_DETAILS="group/verify/v1/findGroupInfo";
    //退出群组
    String BASE_BACK_GROUP="group/verify/v1/retreat";
    //解散群组
    String BASE_DELETE_GROUP="group/verify/v1/disbandGroup";
    //修改群简介
    String BASE_UPDATE_JIANJIE="group/verify/v1/modifyGroupDescription";
    //申请进群
    String BASE_ADD_GROUP="group/verify/v1/applyAddGroup";
    //群通知
    String BASE_GROUP_NOTICE="group/verify/v1/findGroupNoticePageList";
    //审核群申请
    String BASE_AUDIT="group/verify/v1/reviewGroupApply";
    //检测是否是我的好友
    String BASE_ISFRIEND="chat/verify/v1/checkMyFriend";
    //添加好友
    String BASE_ADD_FRIEND="chat/verify/v1/addFriend";
    //查询群组所有用户
    String BASE_QUERY_ALLUSERS="group/verify/v1/findGroupMemberList";
    //调整群成员角色
    String BASE_UPDATE_GROUP="group/verify/v1/modifyPermission";
    //移除群成员
    String BASE_DETELE_GROUPUSER="group/verify/v1/removeGroupMember";
    //邀请加群
    String BASE_INVITE_GROUP="group/verify/v1/inviteAddGroup";


    //我的
    //我的帖子
    String MY_Post = "community/verify/v1/findMyPostById";
    //删除帖子
    String DELETE_POST = "community/verify/v1/deletePost";
    //我的收藏
    String Base_Info_Collection="user/verify/v1/findAllInfoCollection";
    //我的关注
    String Base_Find_Follow="user/verify/v1/findFollowUserList";
    //我的通知
    String Base_Tong_Zhi="tool/verify/v1/findSysNoticeList";
    //根据用户id查询信息
    String BASE_BYID = "user/verify/v1/getUserInfoByUserId";
    //查询用户任务列表
    String TASK_LIST = "user/verify/v1/findUserTaskList";
    //做任务
    String DO_TASK = "user/verify/v1/doTheTask";
    //签到
    String USER_SIGN = "user/verify/v1/userSign";
    //当天签到状态
    String FIND_USER_SIGN = "user/verify/v1/findUserSignStatus";
    //查询当月签到日期
    String FIND_RECORDING = "user/verify/v1/findUserSignRecording";
    //查询连续签到日期
    String SIGN_DAY = "user/verify/v1/findContinuousSignDays";
    //绑定微信
    String BIND_WX = "user/verify/v1/bindWeChat";
    //查询是否绑定微信
    String BING_WX_CHAT = "user/verify/v1/whetherToBindWeChat";
    //查询用户积分
    String USER_INTEGRAL = "user/verify/v1/findUserIntegral";
    //用户积分详情
    String USER_RECORD = "user/verify/v1/findUserIntegralRecord";
}
