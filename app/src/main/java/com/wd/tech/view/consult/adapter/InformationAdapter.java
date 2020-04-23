package com.wd.tech.view.consult.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.ConsultDetailsBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.util.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {

    List<ConsultDetailsBean.ResultBean.InformationListBean> informationList;

    public InformationAdapter(List<ConsultDetailsBean.ResultBean.InformationListBean> informationList) {
        this.informationList = informationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_info, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConsultDetailsBean.ResultBean.InformationListBean info = informationList.get(position);
        GlideUtils.getPhoto(info.getThumbnail(),holder.infoImage);
        holder.infoText.setText(info.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(info.getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.info_image)
        ImageView infoImage;
        @BindView(R.id.info_text)
        TextView infoText;
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
