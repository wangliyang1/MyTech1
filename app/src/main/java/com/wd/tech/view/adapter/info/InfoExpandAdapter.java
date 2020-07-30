package com.wd.tech.view.adapter.info;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.info.FriendGroupBean;
import com.wd.tech.bean.info.FriendListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/28 0028
 * author:胡锦涛(Administrator)
 * function:
 */
public class InfoExpandAdapter extends BaseExpandableListAdapter {
    private List<FriendListBean.ResultBean> child=new ArrayList<>();
    private List<FriendGroupBean.ResultBean> group;
    private FriendChildAdapter friendChildAdapter=new FriendChildAdapter(child);
    public InfoExpandAdapter(List<FriendGroupBean.ResultBean> result) {
        group = result;
    }

    public void addAllChild(int position, List<FriendListBean.ResultBean> mchild) {
        child.clear();
        child=mchild;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child==null?0:1;
    }

    @Override
    public FriendGroupBean.ResultBean getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GViewHolder gViewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.infoex_g, null);
            gViewHolder = new GViewHolder(convertView);
            convertView.setTag(gViewHolder);
        } else {
            gViewHolder = (GViewHolder) convertView.getTag();
        }
        FriendGroupBean.ResultBean resultBean = group.get(groupPosition);
        gViewHolder.gName.setText(resultBean.getGroupName());
        gViewHolder.gNum.setText(0 + "/" + resultBean.getCurrentNumber());
        gViewHolder.gLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean closed = resultBean.isClosed();
                closed=!closed;
                resultBean.setClosed(closed);
                if (onClickListener != null) {
                    onClickListener.onClick(groupPosition, resultBean.getGroupId());
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CViewHolder cViewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.infoex_c, null);
            cViewHolder=new CViewHolder(convertView);
            convertView.setTag(cViewHolder);
        }else {
            cViewHolder= (CViewHolder) convertView.getTag();
        }
        cViewHolder.nameRc.setLayoutManager(new LinearLayoutManager(parent.getContext()));
        friendChildAdapter = new FriendChildAdapter(child);
        cViewHolder.nameRc.setAdapter(friendChildAdapter);
        friendChildAdapter.notifyDataSetChanged();
        friendChildAdapter.setOnClickListener(new FriendChildAdapter.OnClickListener() {
            @Override
            public void onClick(int id, String head, String name) {
                if (childClickListener != null) {
                    childClickListener.onChildClick(id,head,name);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, int groupId);
    }

    static class GViewHolder {
        @BindView(R.id.g_ll)
        RelativeLayout gLl;
        @BindView(R.id.g_name)
        TextView gName;
        @BindView(R.id.g_num)
        TextView gNum;

        GViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class CViewHolder {
        @BindView(R.id.name_rc)
        RecyclerView nameRc;
        CViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    ChildClickListener childClickListener;

    public void setChildClickListener(ChildClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }

    public interface ChildClickListener{
        void onChildClick(int id, String head, String name);
    }
}
