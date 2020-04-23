package com.wd.tech.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.consult.RecommendListBean;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.util.GlideUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.Viewholder> {

    List<RecommendListBean.ResultBean> result;

    public ConsultAdapter(List<RecommendListBean.ResultBean> result) {
        this.result = result;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consult_item,null);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        RecommendListBean.ResultBean resultBean = result.get(position);
        if (resultBean.getWhetherAdvertising()==1){
            holder.itemRela1.setVisibility(View.VISIBLE);
            holder.itemRela2.setVisibility(View.GONE);
            RecommendListBean.ResultBean.InfoAdvertisingVoBean infoAdvertisingVo = resultBean.getInfoAdvertisingVo();
            GlideUtils.getPhoto(infoAdvertisingVo.getPic(),holder.consultItemImage);
        }else {
            holder.itemRela1.setVisibility(View.GONE);
            holder.itemRela2.setVisibility(View.VISIBLE);
            GlideUtils.getPhoto(resultBean.getThumbnail(),holder.itemImage);
            holder.itemTitle.setText(resultBean.getTitle());

            holder.itemSummary.setText(resultBean.getSummary());

            holder.itemSource.setText(resultBean.getSource());

            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("M月dd日");
            String format = simpleDateFormat1.format(resultBean.getReleaseTime());
            holder.itemTime.setText(format);

            holder.itemCollection.setText(resultBean.getCollection()+"");
            holder.itemShare.setText(resultBean.getShare()+"");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultBean.getWhetherAdvertising()==1){
                    RecommendListBean.ResultBean.InfoAdvertisingVoBean infoAdvertisingVo = resultBean.getInfoAdvertisingVo();
                    int id = infoAdvertisingVo.getId();
                    listener.onItemClick(id+"");
                }else {
                    listener.onItemClick(resultBean.getId()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_summary)
        TextView itemSummary;
        @BindView(R.id.item_source)
        TextView itemSource;
        @BindView(R.id.item_time)
        TextView itemTime;
        @BindView(R.id.item_collection_image)
        ImageView itemCollectionImage;
        @BindView(R.id.item_collection)
        TextView itemCollection;
        @BindView(R.id.item_share_image)
        ImageView itemShareImage;
        @BindView(R.id.item_share)
        TextView itemShare;
        @BindView(R.id.item_rela2)
        RelativeLayout itemRela2;
        @BindView(R.id.consult_item_image)
        ImageView consultItemImage;
        @BindView(R.id.item_rela1)
        RelativeLayout itemRela1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    OnRecyclerItemClickListener listener;

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }
}
