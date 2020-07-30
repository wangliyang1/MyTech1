package com.wd.tech.view.adapter.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.my.TongZhiBean;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2020/5/5 0005下午 4:44202005
 * 邮箱:2094158527@qq.com
 * 作者:王黎杨
 * 类功能:
 */
public class TongZhiAdapter extends RecyclerView.Adapter<TongZhiAdapter.TongViewHolder> {

    private List<TongZhiBean.ResultBean> result;

    public TongZhiAdapter(List<TongZhiBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public TongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tong_zhi, parent, false);
        return new TongViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TongViewHolder holder, int position) {
        TongZhiBean.ResultBean resultBean = result.get(position);
        long createTime = resultBean.getCreateTime();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:dd");
        String format1 = format.format(createTime);
        holder.time.setText(format1);
        holder.name.setText(resultBean.getContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class TongViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.name)
        TextView name;
        public TongViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
