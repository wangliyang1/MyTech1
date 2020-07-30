package com.wd.tech.view.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.info.CreateGroupActivity;
import com.wd.tech.view.activity.info.InfoSelefriendActivity;
import com.wd.tech.view.fragment.info.InfoItFragment;
import com.wd.tech.view.fragment.info.LinkManFragment;
import com.wd.tech.view.fragment.info.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*
*消息
* */
public class InfoFragment extends BaseFragment<TechPresenter> {
    List<Fragment> fglist = new ArrayList<>();
    @BindView(R.id.info_rg)
    RadioGroup infoRg;
    @BindView(R.id.jia)
    ImageView jia;
    @BindView(R.id.info_vp)
    ViewPager infoVp;
    private PopupWindow popupWindow;
    @Override
    protected void initView(View view) {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initData() {
        fglist.clear();
        fglist.add(new InfoItFragment());
        fglist.add(new LinkManFragment());
        //设置适配器
        infoVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fglist.get(position);
            }

            @Override
            public int getCount() {
                return fglist.size();
            }
        });
        infoRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.info_rb1:
                        infoVp.setCurrentItem(0);
                        break;
                    case R.id.info_rb2:
                        infoVp.setCurrentItem(1);
                        break;
                }
            }
        });
        infoVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                infoRg.check(infoRg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.jia)
    public void onViewClicked() {
        showPopupWindow();
    }
    //弹出框
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showPopupWindow() {
        //加载布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popupjia, null);
        popupWindow = new PopupWindow(view,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(view);
        //设置各个控件的点击响应
        LinearLayout addFriend = view.findViewById(R.id.add_friend);
        LinearLayout create_group = view.findViewById(R.id.create_group);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        //添加朋友
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InfoSelefriendActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
        create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建群组
                Intent intent = new Intent(getContext(), CreateGroupActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
        //是否具有获取焦点的能力
        popupWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, null);
        popupWindow.showAsDropDown(rootview,500,200, Gravity.RIGHT);
    }
    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}
