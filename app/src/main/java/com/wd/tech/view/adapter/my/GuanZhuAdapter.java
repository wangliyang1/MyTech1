package com.wd.tech.view.adapter.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.my.GuanZhuBean;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2020/5/5 0005下午 4:00202005
 * 邮箱:2094158527@qq.com
 * 作者:王黎杨
 * 类功能:
 */
public class GuanZhuAdapter extends RecyclerView.Adapter<GuanZhuAdapter.GuanViewHolder> {
    private List<GuanZhuBean.ResultBean> result;

    public GuanZhuAdapter(List<GuanZhuBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public GuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_guan_zhu, parent, false);
        return new GuanViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GuanViewHolder holder, int position) {
        GuanZhuBean.ResultBean resultBean = result.get(position);
        holder.name.setText(resultBean.getNickName());
        holder.test.setText(resultBean.getSignature());
        GlideUtils.getPhoto(resultBean.getHeadPic(),holder.img);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class GuanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.test)
        TextView test;
        public GuanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
