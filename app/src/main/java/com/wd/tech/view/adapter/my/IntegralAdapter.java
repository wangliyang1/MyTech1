package com.wd.tech.view.adapter.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.my.IntegralRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2020/5/5 0005下午 4:00202005
 * 邮箱:2094158527@qq.com
 * 作者:王黎杨
 * 类功能:
 */
public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.GuanViewHolder> {

    List<IntegralRecordBean.ResultBean> result;

    public IntegralAdapter(List<IntegralRecordBean.ResultBean> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public GuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_integral, parent, false);
        return new GuanViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GuanViewHolder holder, int position) {
        IntegralRecordBean.ResultBean resultBean = result.get(position);
        //判断用户类型
        switch (resultBean.getType()){
            case 1:holder.inteName.setText("签到成功");break;
            case 2:holder.inteName.setText("评论成功");break;
            case 3:holder.inteName.setText("分享成功");break;
            case 4:holder.inteName.setText("发帖成功");break;
            case 5:holder.inteName.setText("抽奖收入");break;
            case 6:holder.inteName.setText("付费资讯");break;
            case 7:holder.inteName.setText("抽奖支出");break;
            case 8:holder.inteName.setText("完善个人信息（单次奖励）");break;
            case 9:holder.inteName.setText("查看广告");break;
            case 10:holder.inteName.setText("绑定第三方");break;
        }
        //判断最后修改日期
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat1.format(resultBean.getCreateTime());
        holder.inteTime.setText(format);
        //判断是收入还是支出
        if (resultBean.getDirection()==1){
            holder.inteFen.setText("+ "+resultBean.getAmount());
            holder.inteFen.setTextColor(holder.itemView.getResources().getColor(R.color.ren_in));
        }else {
            holder.inteFen.setText("- "+resultBean.getAmount());
            holder.inteFen.setTextColor(holder.itemView.getResources().getColor(R.color.blue));
        }

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class GuanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.inte_name)
        TextView inteName;
        @BindView(R.id.inte_time)
        TextView inteTime;
        @BindView(R.id.inte_fen)
        TextView inteFen;
        public GuanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
