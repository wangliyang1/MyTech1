package com.wd.tech.view.adapter.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.my.MyPostBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.util.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/5/5 16:55
 * author:王黎杨(Administrator)
 * function:我的帖子适配器
 */
public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.ViewHolder> {
    List<MyPostBean.ResultBean> result;

    public MyPostAdapter(List<MyPostBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_post, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyPostBean.ResultBean resultBean = result.get(position);
        //正文
        holder.postContent.setText(resultBean.getContent());
        //日期
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = simpleDateFormat1.format(resultBean.getPublishTime());
        holder.postTime.setText(format);
        //点赞数
        holder.postZanText.setText(resultBean.getPraise()+"");
        //评论数
        holder.postXiaoxiText.setText(resultBean.getComment()+"");
        //图片
        if (resultBean.getFile()==""){
            holder.postImage.setVisibility(View.GONE);
        }else {
            holder.postImage.setVisibility(View.VISIBLE);
            GlideUtils.getPhoto(resultBean.getFile(),holder.postImage);
        }
        //删除
        holder.postCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(resultBean.getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_content)
        TextView postContent;
        @BindView(R.id.post_image)
        ImageView postImage;
        @BindView(R.id.post_time)
        TextView postTime;
        @BindView(R.id.post_cal)
        TextView postCal;
        @BindView(R.id.post_zan_image)
        ImageView postZanImage;
        @BindView(R.id.post_zan_text)
        TextView postZanText;
        @BindView(R.id.post_xiaoxi_image)
        ImageView postXiaoxiImage;
        @BindView(R.id.post_xiaoxi_text)
        TextView postXiaoxiText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnRecyclerItemClickListener listener;

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }
}
