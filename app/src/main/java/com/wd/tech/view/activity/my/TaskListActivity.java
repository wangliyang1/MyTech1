package com.wd.tech.view.activity.my;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.api.MyUrls;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.my.BingWXBean;
import com.wd.tech.bean.my.FindSingRecordingBean;
import com.wd.tech.bean.my.UserTaskBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.MainActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 我的任务
 * */
public class TaskListActivity extends BaseActivity<TechPresenter> {


    @BindView(R.id.task_qiandao)
    TextView taskQiandao;
    @BindView(R.id.task_pingjia)
    TextView taskPingjia;
    @BindView(R.id.task_fatie)
    TextView taskFatie;
    @BindView(R.id.task_fenxiang)
    TextView taskFenxiang;
    @BindView(R.id.task_chakan)
    TextView taskChakan;
    @BindView(R.id.task_wanshan)
    TextView taskWanshan;
    @BindView(R.id.task_bangding)
    TextView taskBangding;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        //查询任务列表
        mPresenter.getNoParams(MyUrls.TASK_LIST, UserTaskBean.class);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_task_list;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserTaskBean && TextUtils.equals("0000", ((UserTaskBean) o).getStatus())) {
            List<UserTaskBean.ResultBean> list = ((UserTaskBean) o).getResult();
            for (int i = 0; i < list.size(); i++) {
                UserTaskBean.ResultBean resultBean = list.get(i);
                selected(resultBean.getTaskId(),resultBean.getTaskIntegral(),resultBean.getStatus());
            }
        }
        if (o instanceof FindSingRecordingBean && TextUtils.equals("0000",((FindSingRecordingBean) o).getStatus())){
            List<String> result = ((FindSingRecordingBean) o).getResult();
            Intent intent = new Intent(this,MyDateActivity.class);
            intent.putExtra("tmd",(Serializable) result);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    private void selected(int taskId,int taskIntegral,int status){
        switch (taskId){//根据任务id判断是哪个任务
            case 1001:
                if (status==1){//根据状态判断是否已完成任务
                    taskQiandao.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskQiandao.setText("已完成");
                    taskQiandao.setTextColor(Color.WHITE);
                }else {
                    taskQiandao.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskQiandao.setText("去签到");
                    taskQiandao.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
            case 1002:
                if (status==1){//根据状态判断是否已完成任务
                    taskPingjia.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskPingjia.setText("已完成");
                    taskPingjia.setTextColor(Color.WHITE);
                }else {
                    taskPingjia.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskPingjia.setText("去评价");
                    taskPingjia.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
            case 1003:
                if (status==1){//根据状态判断是否已完成任务
                    taskFatie.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskFatie.setText("已完成");
                    taskFatie.setTextColor(Color.WHITE);
                }else {
                    taskFatie.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskFatie.setText("去发帖");
                    taskFatie.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
            case 1004:
                if (status==1){//根据状态判断是否已完成任务
                    taskFenxiang.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskFenxiang.setText("已完成");
                    taskFenxiang.setTextColor(Color.WHITE);
                }else {
                    taskFenxiang.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskFenxiang.setText("去分享");
                    taskFenxiang.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
            case 1005:
                if (status==1){//根据状态判断是否已完成任务
                    taskChakan.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskChakan.setText("已完成");
                    taskChakan.setTextColor(Color.WHITE);
                }else {
                    taskChakan.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskChakan.setText("去查看");
                    taskChakan.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
            case 1006:
                if (status==1){//根据状态判断是否已完成任务
                    taskWanshan.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskWanshan.setText("已完成");
                    taskWanshan.setTextColor(Color.WHITE);
                }else {
                    taskWanshan.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskWanshan.setText("去完善");
                    taskWanshan.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
            case 1007:
                if (status==1){//根据状态判断是否已完成任务
                    taskBangding.setBackground(this.getResources().getDrawable(R.drawable.task_check));
                    taskBangding.setText("已完成");
                    taskBangding.setTextColor(Color.WHITE);
                }else {
                    taskBangding.setBackground(this.getResources().getDrawable(R.drawable.task_nocheck));
                    taskBangding.setText("去绑定");
                    taskBangding.setTextColor(getResources().getColor(R.color.colorBlue));
                }
                break;
        }
    }

    @OnClick({R.id.task_qiandao, R.id.task_pingjia, R.id.task_fatie, R.id.task_fenxiang, R.id.task_chakan, R.id.task_wanshan, R.id.task_bangding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_qiandao://跳转到签到页面
                //查询当月签到的日期
                mPresenter.getNoParams(MyUrls.FIND_RECORDING, FindSingRecordingBean.class);
                break;
            case R.id.task_pingjia://返回到主页
                Start();
                break;
            case R.id.task_fatie://返回到社区页面
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("task","123");
                startActivity(intent);
                finish();
                break;
            case R.id.task_fenxiang://返回到主页
                Start();
                break;
            case R.id.task_chakan://返回到主页
                Start();
                break;
            case R.id.task_wanshan://进入到个人信息界面
                startActivity(this,MyActivity.class);
                finish();
                break;
            case R.id.task_bangding://进入到设置页面
                startActivity(this,SheActivity.class);
                finish();
                break;
        }
    }
    private void Start(){
        startActivity(this, MainActivity.class);
        finish();
    }
}
