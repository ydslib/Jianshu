package com.yds.mainmodule.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yds.mainmodule.fragment.AttentionFragment;
import com.yds.mainmodule.fragment.HomeFragment;
import com.yds.mainmodule.fragment.JevelFragment;
import com.yds.mainmodule.fragment.MeFragment;
import com.yds.mainmodule.fragment.NotificationFragment;

import java.util.List;

/**
 * Created by yds
 * on 2019/9/18.
 */
public class HomeTabFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    public HomeTabFragmentPagerAdapter(FragmentManager fm,List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


}
