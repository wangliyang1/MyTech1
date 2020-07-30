package com.wd.tech.view.adapter.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.my.InfoCollectionBean;
import com.wd.tech.util.GlideUtils;
import com.wd.tech.util.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2020/4/29 0029下午 9:15202004
 * 邮箱:2094158527@qq.com
 * 作者:王黎杨
 * 类功能:
 */
public class InfoCollectionAdapter extends RecyclerView.Adapter<InfoCollectionAdapter.ViewHolder> {
    private List<InfoCollectionBean.ResultBean> result;

    public InfoCollectionAdapter(List<InfoCollectionBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_info_collection, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InfoCollectionBean.ResultBean resultBean = result.get(position);
        long createTime = resultBean.getCreateTime();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:dd");
        String format1 = format.format(createTime);
        GlideUtils.getPhoto(resultBean.getThumbnail(),holder.img);
        holder.title.setText(resultBean.getTitle());
        holder.time.setText(format1);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
