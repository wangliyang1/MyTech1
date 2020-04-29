package com.wd.tech.view.adapter.commuity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.community.CommunityListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/26 0026
 * author:王黎杨
 * function:
 */
public class ComPlItemAdapter extends RecyclerView.Adapter<ComPlItemAdapter.MyViewHolder> {

    private List<CommunityListBean.ResultBean.CommunityCommentVoListBean> list=new ArrayList<>();

    public ComPlItemAdapter(List<CommunityListBean.ResultBean.CommunityCommentVoListBean> voList) {
        if (voList.size()>0&&voList.size()<4){
            list=voList;
        }else {
            for (int i = 0; i < 3; i++) {
                list.add(voList.get(i));
            }
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.compl_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommunityListBean.ResultBean.CommunityCommentVoListBean communityCommentVoListBean = list.get(position);
        holder.name.setText(communityCommentVoListBean.getNickName());
        holder.content.setText(communityCommentVoListBean.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.content)
        TextView content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
