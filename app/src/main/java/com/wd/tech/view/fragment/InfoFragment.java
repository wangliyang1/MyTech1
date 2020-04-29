package com.wd.tech.view.fragment;

import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.fragment.info.LinkManFragment;
import com.wd.tech.view.fragment.info.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
*消息
* */
public class InfoFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.info_rg)
    RadioGroup infoRg;
    @BindView(R.id.info_vp)
    ViewPager infoVp;
    List<Fragment> fglist = new ArrayList<>();
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
        MessageFragment messageFragment = new MessageFragment();
        LinkManFragment linkManFragment = new LinkManFragment();
        fglist.add(messageFragment);
        fglist.add(linkManFragment);

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
        //vp 联动 rg
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
        infoRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.info_message:
                        infoVp.setCurrentItem(0);
                        break;
                    case R.id.info_man:
                        infoVp.setCurrentItem(1);
                        break;
                }
            }
        });
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
