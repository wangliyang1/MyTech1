package com.wd.tech.view.adapter.commuity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.community.CommPlBean;
import com.wd.tech.util.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/26 0026
 * author:王黎杨
 * function:
 */
public class ComPllistAdapter extends RecyclerView.Adapter<ComPllistAdapter.MyViewHolder> {

    private List<CommPlBean.ResultBean> list;

    public ComPllistAdapter(List<CommPlBean.ResultBean> result) {

        list = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.compl_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommPlBean.ResultBean resultBean = list.get(position);
        holder.name.setText(resultBean.getNickName());
        holder.content.setText(resultBean.getContent());
        long commentTime = resultBean.getCommentTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String format1 = format.format(commentTime);
        holder.time.setText(format1);
        GlideUtils.getPhoto(resultBean.getHeadPic(),holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
