package com.wd.tech.view.consult.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.FindPlaterBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.util.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 *
 */
public class FindPlateAdapter extends RecyclerView.Adapter<FindPlateAdapter.ViewHolder> {

    List<FindPlaterBean.ResultBean> list;

    public FindPlateAdapter(List<FindPlaterBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_plate, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindPlaterBean.ResultBean resultBean = list.get(position);
        GlideUtils.getPhoto(resultBean.getPic(),holder.findPlateImage);
        holder.findPlateName.setText(resultBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.find_plate_image)
        ImageView findPlateImage;
        @BindView(R.id.find_plate_name)
        TextView findPlateName;

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
