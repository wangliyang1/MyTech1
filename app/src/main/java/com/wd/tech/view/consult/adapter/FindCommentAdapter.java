package com.wd.tech.view.consult.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.FindCommentBean;
import com.wd.tech.util.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindCommentAdapter extends RecyclerView.Adapter<FindCommentAdapter.ViewHolder> {

    List<FindCommentBean.ResultBean> result;

    public FindCommentAdapter(List<FindCommentBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_commlist, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindCommentBean.ResultBean resultBean = result.get(position);
        //头像
        GlideUtils.getCiclePhoto(resultBean.getHeadPic(),holder.commlistImage);
        //昵称
        holder.commlistTitle.setText(resultBean.getNickName());
        //时间
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String format = simpleDateFormat1.format(resultBean.getCommentTime());
        holder.commlistTime.setText(format);
        //内容
        holder.commlistContent.setText(resultBean.getContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }



    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commlist_image)
        ImageView commlistImage;
        @BindView(R.id.commlist_title)
        TextView commlistTitle;
        @BindView(R.id.commlist_time)
        TextView commlistTime;
        @BindView(R.id.commlist_content)
        TextView commlistContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
