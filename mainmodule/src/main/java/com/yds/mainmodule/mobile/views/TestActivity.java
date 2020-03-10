package com.yds.mainmodule.mobile.views;

/**
 * Created by yds
 * on 2020/2/20.
 */
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.yds.jianshulib.adapter.BaseTabFragmentPagerAdapter;
import com.yds.mainmodule.R;
import com.yds.mainmodule.fragment.KojimaFragment;
import com.yds.mainmodule.fragment.MakeListFragment;
import com.yds.mainmodule.fragment.RecommendFragment;
import com.yds.mainmodule.fragment.SerialFragment;
import com.yds.mainmodule.fragment.ThematicFragment;

import java.util.ArrayList;
import java.util.List;


public class TestActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();
    }
    private void initView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);

        mTabLayout.addTab(mTabLayout.newTab().setText("打榜"));
        mTabLayout.addTab(mTabLayout.newTab().setText("推荐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("小岛"));
        mTabLayout.addTab(mTabLayout.newTab().setText("专题"));
        mTabLayout.addTab(mTabLayout.newTab().setText("连载"));

        mFragmentList = new ArrayList<>(5);
        mFragmentList.add(new MakeListFragment());
        mFragmentList.add(new RecommendFragment());
        mFragmentList.add(new KojimaFragment());
        mFragmentList.add(new ThematicFragment());
        mFragmentList.add(new SerialFragment());

        mAdapter = new BaseTabFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }
}
