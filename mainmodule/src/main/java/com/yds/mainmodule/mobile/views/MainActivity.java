package com.yds.mainmodule.mobile.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yds.jianshu.base.mvp.BaseActivity;
import com.yds.jianshu.utils.ObjectUtil;
import com.yds.jianshu.utils.statusbar.StatusBarUtil;
import com.yds.mainmodule.R;
import com.yds.mainmodule.adapter.HomeTabFragmentPagerAdapter;
import com.yds.mainmodule.fragment.AttentionFragment;
import com.yds.mainmodule.fragment.HomeFragment;
import com.yds.mainmodule.fragment.JevelFragment;
import com.yds.mainmodule.fragment.MeFragment;
import com.yds.mainmodule.fragment.NotificationFragment;
import com.yds.mainmodule.mobile.contract.MainContract;
import com.yds.mainmodule.mobile.presenters.MainPresenter;
import com.yds.mainmodule.onepixel.OnePixelService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainContract.IMainPresenter> implements MainContract.IMainView {
    private static final String TAG = "[MainActivity]";

    private TabLayout mBottomTabLayout;
    private ViewPager mBottomViewPager;

    private List<Fragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;

    private String[] mTabTitles = new String[]{"首页", "关注", "简书钻", "消息", "我的"};
    private int[] mTabImages = new int[]{
            R.drawable.tabbar_subscription_img_selecter,
            R.drawable.tabbar__follow_img_selecter,
            R.drawable.tabbar_jewel_img_selecter,
            R.drawable.tabbar_notification_img_selecter,
            R.drawable.tabbar_me_img_selecter
    };

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        mBottomTabLayout = findViewById(R.id.main_bottom_tabLayout);
        mBottomViewPager = findViewById(R.id.bottom_tab_viewpager);
    }

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
    }

    @Override
    protected void initEvents() {
        startOnePixelService();
        setTabs(mTabTitles, mTabImages);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new AttentionFragment());
        mFragmentList.add(new JevelFragment());
        mFragmentList.add(new NotificationFragment());
        mFragmentList.add(new MeFragment());

        mAdapter = new HomeTabFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mBottomViewPager.setAdapter(mAdapter);
        mBottomViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mBottomTabLayout));
        mBottomTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mBottomViewPager));
//        mBottomTabLayout.setupWithViewPager(mBottomViewPager);

//        TabLayout.Tab homeTab = mBottomTabLayout.getTabAt(0);
//        TabLayout.Tab attentionTab = mBottomTabLayout.getTabAt(1);
//        TabLayout.Tab jewelTab = mBottomTabLayout.getTabAt(2);
//        TabLayout.Tab notificationTab = mBottomTabLayout.getTabAt(3);
//        TabLayout.Tab meTab = mBottomTabLayout.getTabAt(4);
//
//        homeTab.setIcon(R.drawable.tabbar_subscription_img_selecter);
//        attentionTab.setIcon(R.drawable.tabbar__follow_img_selecter);
//        jewelTab.setIcon(R.drawable.tabbar_jewel_img_selecter);
//        notificationTab.setIcon(R.drawable.tabbar_notification_img_selecter);
//        meTab.setIcon(R.drawable.tabbar_me_img_selecter);


    }

    @Override
    protected MainContract.IMainPresenter setPresenter() {
        return new MainPresenter();
    }

    private void startOnePixelService() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, OnePixelService.class);
        startService(intent);
    }

    private void setTabs(String[] bottomTabTitles, int[] bottomTabImages) {
        if (bottomTabImages != null && bottomTabTitles != null) {
            for (int i = 0; i < bottomTabTitles.length; i++) {
                TabLayout.Tab tab = mBottomTabLayout.newTab();
                View view = getLayoutInflater().inflate(R.layout.main_module_layout_home_tab_item, null);
                tab.setCustomView(view);

                TextView tabTitle = view.findViewById(R.id.home_item_tv);
                tabTitle.setText(bottomTabTitles[i]);


                ImageView tabImage = view.findViewById(R.id.home_item_iv);
                tabImage.setImageResource(bottomTabImages[i]);
                if (i == 0) {
                    mBottomTabLayout.addTab(tab, true);
                } else {
                    mBottomTabLayout.addTab(tab, false);
                }
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
