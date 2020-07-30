package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.api.MyApp;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.community.CommunityZanBean;
import com.wd.tech.bean.info.ChatBean;
import com.wd.tech.bean.info.FriendInfoByIdBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.util.RxPartMapUtils;
import com.wd.tech.view.adapter.info.MsgAdapter;
import com.wd.tech.widget.Msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Message;
import okhttp3.RequestBody;

public class ChatMsgActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.set)
    ImageView set;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.ed)
    EditText ed;
    @BindView(R.id.bt)
    Button bt;
    private SharedPreferences sp;
    private String myName;
    List<Msg.Message> list=new ArrayList<>();
    private String headPic;
    private String head;
    private MsgAdapter msgAdapter;
    private String phone;
    private String nickName;
    private int userId;
    private int id;
    @Override
    protected void initData() {
        //注册接收消息
        JMessageClient.registerEventReceiver(this);
        getSupportActionBar().hide();
        rc.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            nickName = intent.getStringExtra("name");
            head = intent.getStringExtra("head");
            name.setText(nickName);
            //查询好友信息
            HashMap<String, Object> map = new HashMap<>();
            map.put("friend", id);
            mPresenter.getDoParams(MyUrls.BASE_FRIENDINFO_ID, FriendInfoByIdBean.class,map);
            //查询对话记录
            HashMap<String, Object> map1=new HashMap<>();
            map1.put("friendUid", id);
            map1.put("page",1);
            map1.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_CHAT, ChatBean.class,map1);
        }
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        myName = sp.getString("nickName", "");
        headPic = sp.getString("headPic", "");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_chat_msg;
    }

    @Override
    protected void DestroyActivity() {
        JMessageClient.unRegisterEventReceiver(this);
    }

    @Override
    public void onSuccess(Object o) {
        //好友信息
        if (o instanceof FriendInfoByIdBean&&TextUtils.equals("0000",((FriendInfoByIdBean) o).getStatus())){
            FriendInfoByIdBean.ResultBean result = ((FriendInfoByIdBean) o).getResult();
            phone = result.getPhone();
            userId = result.getUserId();
            //消息界面关闭通知
            JMessageClient.enterSingleConversation(phone);
        }
        //查看对话记录
        if (o instanceof ChatBean&& TextUtils.equals("0000",((ChatBean) o).getStatus())){
            List<ChatBean.ResultBean> result = ((ChatBean) o).getResult();
            for (int i=result.size()-1; i>=0 ; i--) {
                ChatBean.ResultBean resultBean = result.get(i);
                int direction = resultBean.getDirection();
                Msg.Message messa=null;
                String s=null;
                try {
                    s = RsaCoder.decryptByPublicKey(resultBean.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (direction==1){
                    messa = Msg.getInstance().getMessage(s,headPic,myName,Msg.TYPE_SENT);
                }else {
                    messa = Msg.getInstance().getMessage(s,head,nickName,Msg.TYPE_RECEIVED);
                }
                list.add(messa);
            }
            msgAdapter = new MsgAdapter(list);
            rc.setAdapter(msgAdapter);
        }
        //发送消息
        if (o instanceof CommunityZanBean &&TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(this, ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeBack, R.id.bt,R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack:
                finish();
                break;
            case R.id.bt:
                String content = ed.getText().toString();
                if (!"".equals(content)) {
                    try {
                        //发送消息
                        String context = RsaCoder.encryptByPublicKey(content);
                        HashMap<String, RequestBody> map = new HashMap<>();
                        map.put("receiveUid", RxPartMapUtils.toRequestBodyOfText(userId+""));
                        map.put("content", RxPartMapUtils.toRequestBodyOfText(context));
                        mPresenter.postFileParams(MyUrls.BASE_SEND_MSG, CommunityZanBean.class,map);
                        Msg.Message messa = Msg.getInstance().getMessage(content,headPic,myName,Msg.TYPE_SENT);
                        Message message = JMessageClient.createSingleTextMessage(phone, MyApp.s1,context);
                        JMessageClient.sendMessage(message);
                        list.add(messa);
                        msgAdapter.notifyItemInserted(list.size()-1);
                        rc.scrollToPosition(list.size()-1);
                        ed.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.set:
                Intent intent = new Intent(this, FriendSetActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
        }
    }
    /*接收到的消息*/
    public void onEvent(MessageEvent event) {
        final Message message = event.getMessage();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (message.getContentType()) {
                    case text:
                        TextContent textContent = (TextContent) message.getContent();
                        String string = textContent.getText().toString();
                        try {
                            String s = RsaCoder.decryptByPublicKey(string);
                            Msg.Message messa = Msg.getInstance().getMessage(string,head,nickName,Msg.TYPE_RECEIVED);
                            list.add(messa);
                            msgAdapter.notifyItemInserted(list.size()-1);
                            rc.scrollToPosition(list.size()-1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }

            }
        });
    }
}
