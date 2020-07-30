package com.wd.tech.view.adapter.info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;
import com.wd.tech.widget.Msg;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/28 0028
 * author:王黎杨(Administrator)
 * function:群组聊天
 */
public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {


    private List<Msg.Message> list;

    public GroupChatAdapter(List<Msg.Message> list) {

        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Msg.Message message = list.get(position);
        if (message.getType()== 2) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftName.setText(message.getName());
            GlideUtils.getCiclePhoto(message.getHead(), holder.leftHead);
            holder.leftMsg.setText(message.getContent());
        } else if (message.getType()== 1) {
            GlideUtils.getCiclePhoto(message.getHead(), holder.rightHead);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightName.setText(message.getName());
            holder.rightMsg.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_head)
        ImageView leftHead;
        @BindView(R.id.left_name)
        TextView leftName;
        @BindView(R.id.left_msg)
        TextView leftMsg;
        @BindView(R.id.left_layout)
        RelativeLayout leftLayout;
        @BindView(R.id.right_head)
        ImageView rightHead;
        @BindView(R.id.right_name)
        TextView rightName;
        @BindView(R.id.right_msg)
        TextView rightMsg;
        @BindView(R.id.right_layout)
        RelativeLayout rightLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
