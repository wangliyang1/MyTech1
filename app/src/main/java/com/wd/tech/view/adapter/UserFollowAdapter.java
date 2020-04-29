package com.wd.tech.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.community.CommUser0Bean;
import com.wd.tech.bean.community.CommUserBean;
import com.wd.tech.util.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/27 0027
 * author:胡锦涛(Administrator)
 * function:
 */
public class UserFollowAdapter extends RecyclerView.Adapter<UserFollowAdapter.ViewHolder> {
    private CommUser0Bean.ResultBean.CommunityUserVoBean list;


    public UserFollowAdapter(CommUser0Bean.ResultBean.CommunityUserVoBean communityUserVo) {
        list = communityUserVo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.user_info,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String headPic = list.getHeadPic();
        GlideUtils.getCiclePhoto(headPic,holder.ivHead);
        holder.name.setText(list.getNickName());
        holder.desion.setText(list.getSignature());
        holder.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(headPic);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.desion)
        TextView desion;
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
        void onClick(String headpic);
    }
}
