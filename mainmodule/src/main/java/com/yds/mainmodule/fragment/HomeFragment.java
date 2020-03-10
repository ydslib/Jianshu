package com.yds.mainmodule.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yds.jianshulib.adapter.BaseTabFragmentPagerAdapter;
import com.yds.mainmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2019/8/13.
 */
public class HomeFragment extends Fragment {
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_module_fragment_home,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.viewpager);

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

        mAdapter = new BaseTabFragmentPagerAdapter(getChildFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }
}
