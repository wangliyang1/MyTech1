package com.wd.tech.view.consult.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.ConsultDetailsBean;
import com.wd.tech.bean.consult.FindVipBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindVipAdapter extends RecyclerView.Adapter<FindVipAdapter.ViewHolder> {

    List<FindVipBean.ResultBean> result;

    public FindVipAdapter(List<FindVipBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_pay, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindVipBean.ResultBean resultBean = result.get(position);
        holder.payItemText.setText(resultBean.getCommodityName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    static

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pay_item_text)
        TextView payItemText;

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
