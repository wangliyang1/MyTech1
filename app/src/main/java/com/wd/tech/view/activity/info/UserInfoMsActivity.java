package com.wd.tech.view.activity.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.info.CheckFriendBean;
import com.wd.tech.bean.info.InfoSeleFriendBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.util.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户信息页
 */
public class UserInfoMsActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.write)
    TextView write;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.vpi)
    ImageView vpi;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.fen)
    TextView fen;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.birthDay)
    TextView birthDay;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.bt)
    Button bt;
    int tag=0;
    private SharedPreferences sp;
    private int userId;
    private String headPic;
    private String nickName;
    private String signature;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        //获取手机号
        Intent intent = getIntent();
        if (intent != null) {
            String phone = intent.getStringExtra("phone");
            HashMap<String, Object> map = new HashMap<>();
            map.put("phone", phone);
            mPresenter.getDoParams(MyUrls.BASE_SEUSER_BYPHONE, InfoSeleFriendBean.class, map);
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag==1){
                    //聊天
                    Intent chat = new Intent(UserInfoMsActivity.this,ChatMsgActivity.class);
                    chat.putExtra("id",userId);
                    chat.putExtra("name",nickName);
                    chat.putExtra("head",headPic);
                    startActivity(chat);
                }
                if (tag==2){
                    //添加好友
                    Intent add = new Intent(UserInfoMsActivity.this,AddFriendActivity.class);
                    add.putExtra("id",userId);
                    add.putExtra("head",headPic);
                    add.putExtra("name",nickName);
                    if (signature !=null&&!signature.equals("")){
                        add.putExtra("cont",userId);
                    }
                    startActivity(add);
                }
            }
        });
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_user_info_ms;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {//用户信息
        if (o instanceof InfoSeleFriendBean && TextUtils.equals("0000", ((InfoSeleFriendBean) o).getStatus())) {
            InfoSeleFriendBean.ResultBean result = ((InfoSeleFriendBean) o).getResult();
            userId = result.getUserId();
            if (userId == sp.getInt("userId", -1)) {//是否是用户
                write.setVisibility(View.VISIBLE);
                bt.setVisibility(View.GONE);
            } else {//是否是好友
                write.setVisibility(View.GONE);
                bt.setVisibility(View.VISIBLE);
                HashMap<String, Object> map = new HashMap<>();
                map.put("friendUid", userId);
                mPresenter.getDoParams(MyUrls.BASE_ISFRIEND, CheckFriendBean.class, map);
            }
            headPic = result.getHeadPic();
            GlideUtils.getCiclePhoto(headPic,head);
            nickName = result.getNickName();
            name.setText(nickName);
            fen.setText("("+result.getIntegral()+"积分)");
            int whetherVip = result.getWhetherVip();
            if (whetherVip==1){//是VIP
                vpi.setVisibility(View.VISIBLE);
            }else {
                vpi.setVisibility(View.GONE);
            }
            signature = result.getSignature();
            if (signature !=null&&!signature.equals("")){
                content.setText(signature);
            }
            //性别生日
            long birthday = result.getBirthday();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int sex = result.getSex();
            if (sex==1){
                if (birthday==0){
                    birthDay.setText("男");
                }else {
                    String birth = format.format(birthday);
                    birthDay.setText("男("+birth+")");
                }

            }else {
                if (birthday==0){
                    birthDay.setText("女");
                }else {
                    String birth = format.format(birthday);
                    birthDay.setText("女("+birth+")");
                }
            }
            //手机号
            String phon = result.getPhone();
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < phon.length(); i++) {
                char c = phon.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            phone.setText(sb);
            email.setText(result.getEmail());
        }
        //是否是好友
        if (o instanceof CheckFriendBean&&TextUtils.equals("0000",((CheckFriendBean) o).getStatus())){
            if (((CheckFriendBean) o).getFlag()==1){//是好友
                bt.setText("发消息");
                tag=1;
            }else {
                bt.setText("添加为好友");
                tag=2;
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.write, R.id.bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.write:
                break;
            case R.id.bt:
                break;
        }
    }
}
