package com.wd.tech.view.consult.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.FindByTitleBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * 根据标题模糊查询数据
 * */
public class FindTitleAdapter extends RecyclerView.Adapter<FindTitleAdapter.ViewHolder> {

    List<FindByTitleBean.ResultBean> result;

    public FindTitleAdapter(List<FindByTitleBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_title, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindByTitleBean.ResultBean resultBean = result.get(position);
        holder.findTitleName.setText(resultBean.getTitle());
        holder.findTitleSource.setText(resultBean.getSource());

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = simpleDateFormat1.format(resultBean.getReleaseTime());
        holder.findTitleTime.setText(format);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.find_title_name)
        TextView findTitleName;
        @BindView(R.id.find_title_source)
        TextView findTitleSource;
        @BindView(R.id.find_title_time)
        TextView findTitleTime;

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
