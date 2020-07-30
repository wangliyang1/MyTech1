package com.wd.tech.view.activity.info;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.view.fragment.info.FindFriendFragment;
import com.wd.tech.view.fragment.info.FindGroupFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoSelefriendActivity extends BaseActivity {

    @BindView(R.id.comeback)
    ImageView comeback;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<String> stlist=new ArrayList<>();
    private List<Fragment> fglist=new ArrayList<>();
    @Override
    protected void initData() {
        stlist.clear();
        fglist.clear();
        stlist.add("找人");
        stlist.add("找群");
        fglist.add(new FindFriendFragment());
        fglist.add(new FindGroupFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fglist.get(position);
            }

            @Override
            public int getCount() {
                return fglist.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return stlist.get(position);
            }
        });
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_info_selefriend;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick(R.id.comeback)
    public void onViewClicked() {
        finish();
    }
}
