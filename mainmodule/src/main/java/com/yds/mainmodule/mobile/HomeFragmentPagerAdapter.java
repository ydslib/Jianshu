package com.yds.mainmodule.mobile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yds
 * on 2019/8/13.
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public HomeFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int index) {
        return list.get(index);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
