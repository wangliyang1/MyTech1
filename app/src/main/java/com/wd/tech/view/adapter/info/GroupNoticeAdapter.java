package com.wd.tech.view.adapter.info;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.info.GroupNoticeBean;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/5/8 0008
 * author:王黎杨(Administrator)
 * function:群通知
 */
public class GroupNoticeAdapter extends RecyclerView.Adapter<GroupNoticeAdapter.ViewHolder> {

    private List<GroupNoticeBean.ResultBean> result;

    public GroupNoticeAdapter(List<GroupNoticeBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.group_notice, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupNoticeBean.ResultBean resultBean = result.get(position);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        int status = resultBean.getStatus();
        switch (status){
            case 1://待处理
                holder.btNo.setVisibility(View.VISIBLE);
                holder.btOk.setVisibility(View.VISIBLE);
                holder.statue.setVisibility(View.GONE);
                break;
            case 2://通过
                holder.btNo.setVisibility(View.GONE);
                holder.btOk.setVisibility(View.GONE);
                holder.statue.setVisibility(View.VISIBLE);
                holder.statue.setText("已同意");
                break;
            case 3://驳回
                holder.btNo.setVisibility(View.GONE);
                holder.btOk.setVisibility(View.GONE);
                holder.statue.setVisibility(View.VISIBLE);
                holder.statue.setText("已拒绝");
                break;
        }
        holder.time.setText(format.format(resultBean.getNoticeTime()));
        holder.name.setText(resultBean.getNickName());
        GlideUtils.getCiclePhoto(resultBean.getHeadPic(),holder.head);
        int type = resultBean.getType();
        String groupName = resultBean.getGroupName();
        if (type==1){//邀请用户加群
            holder.remoke.setText("邀请进入"+groupName+"群");
        }else {//用户申请进群
            holder.remoke.setText("申请进入"+groupName+"群");
        }
        holder.btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(resultBean.getNoticeId(),1);
                }
            }
        });
        holder.btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(resultBean.getNoticeId(),2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.head)
        ImageView head;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.remoke)
        TextView remoke;
        @BindView(R.id.statue)
        TextView statue;
        @BindView(R.id.bt_ok)
        TextView btOk;
        @BindView(R.id.bt_no)
        TextView btNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onClick(int id, int flag);
    }
}
