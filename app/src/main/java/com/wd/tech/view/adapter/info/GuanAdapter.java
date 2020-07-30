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
 * function:群管理
 */
public class GuanAdapter extends RecyclerView.Adapter<GuanAdapter.ViewHolder>{
    private List<GroupAllUserListBean.ResultBean> guan;

    public GuanAdapter(List<GroupAllUserListBean.ResultBean> guan) {

        this.guan = guan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.gl_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupAllUserListBean.ResultBean resultBean = guan.get(position);
        GlideUtils.getCiclePhoto(resultBean.getHeadPic(),holder.head);
        holder.name.setText(resultBean.getNickName());
    }

    @Override
    public int getItemCount() {
        return guan.size();
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
