package com.wd.tech.view.adapter.info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.FriendNoticeBean;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/21
 * author:王黎杨
 * function:消息适配器
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private List<FriendNoticeBean.ResultBean> result;

    public MessageAdapter(List<FriendNoticeBean.ResultBean> result) {

        this.result = result;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_child, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FriendNoticeBean.ResultBean resultBean = result.get(position);
        holder.frName.setText(resultBean.getFromNickName());
        holder.frMessage.setText(resultBean.getRemark());
        GlideUtils.getPhoto(resultBean.getFromHeadPic(),holder.frImg);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM:dd HH:mm");
        String format = simpleDateFormat.format(resultBean.getNoticeTime());
        holder.frTime.setText(format);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fr_img)
        ImageView frImg;
        @BindView(R.id.fr_name)
        TextView frName;
        @BindView(R.id.fr_time)
        TextView frTime;
        @BindView(R.id.fr_message)
        TextView frMessage;
        @BindView(R.id.xyd)
        TextView xyd;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
