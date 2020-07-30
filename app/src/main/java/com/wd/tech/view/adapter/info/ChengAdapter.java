package com.wd.tech.view.adapter.info;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.info.GroupAllUserListBean;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/5/10 0010
 * author:胡锦涛(Administrator)
 * function:群成员
 */
public class ChengAdapter extends RecyclerView.Adapter<ChengAdapter.ViewHolder> {


    private List<GroupAllUserListBean.ResultBean> cheng;

    public ChengAdapter(List<GroupAllUserListBean.ResultBean> cheng) {

        this.cheng = cheng;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.cy_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupAllUserListBean.ResultBean resultBean = cheng.get(position);
        GlideUtils.getCiclePhoto(resultBean.getHeadPic(),holder.head);
        holder.name.setText(resultBean.getNickName());
    }

    @Override
    public int getItemCount() {
        return cheng.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.head)
        ImageView head;
        @BindView(R.id.name)
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
