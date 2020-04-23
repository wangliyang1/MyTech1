package com.wd.tech.view.consult.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.ConsultDetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.ViewHolder> {
    List<ConsultDetailsBean.ResultBean.PlateBean> plate;

    public PlateAdapter(List<ConsultDetailsBean.ResultBean.PlateBean> plate) {
        this.plate = plate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_guang, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConsultDetailsBean.ResultBean.PlateBean plateBean = plate.get(position);
        holder.guangTv.setText(plateBean.getName());
    }

    @Override
    public int getItemCount() {
        return plate.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guang_tv)
        TextView guangTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
