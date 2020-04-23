package com.wd.tech.view.consult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.contract.OnRecyclerItemClickListener;
import com.wd.tech.contract.Search;
import com.wd.tech.view.consult.fragment.FindSourceFragment;
import com.wd.tech.view.consult.fragment.FindTitleFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 搜索结果页
 * */
public class SearchActivity extends BaseActivity {


    @BindView(R.id.search_jie_edit)
    EditText searchJieEdit;
    @BindView(R.id.search_jie_cancel)
    TextView searchJieCancel;
    @BindView(R.id.search_jie_pager)
    ViewPager searchJiePager;
    @BindView(R.id.search_jie_tab)
    TabLayout searchJieTab;
    @BindView(R.id.search_jie_image)
    ImageView searchJieImage;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private FindTitleFragment findTitleFragment;
    private FindSourceFragment findSourceFragment;

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        strings = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            searchJieEdit.setText(name);
        }

        findTitleFragment = new FindTitleFragment();
        findSourceFragment = new FindSourceFragment();
        fragments.add(findTitleFragment);
        fragments.add(findSourceFragment);
        strings.add("文章");
        strings.add("作者");

        searchJiePager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }
        });
        searchJieTab.setupWithViewPager(searchJiePager);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_search;
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



    @OnClick({R.id.search_jie_image, R.id.search_jie_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_jie_image:
                EventBus.getDefault().post(new Search(searchJieEdit.getText().toString().trim()));
                break;
            case R.id.search_jie_cancel:
                finish();
                break;
        }
    }

}
