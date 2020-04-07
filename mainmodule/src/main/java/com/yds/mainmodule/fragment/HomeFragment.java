package com.yds.mainmodule.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yds.jianshulib.adapter.BaseTabFragmentPagerAdapter;
import com.yds.jianshulib.widget.SearchBarLayout;
import com.yds.mainmodule.R;
import com.yds.mainmodule.adapter.SearchRankAdapter;
import com.yds.mainmodule.bo.SearchRankBO;
import com.yds.mainmodule.dao.SearchRankDAO;

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
    private LinearLayout mSearchContent;
    private AppBarLayout mAppBarLayout;
    private SearchBarLayout mSearchBarLayout;
    private ImageView mBack;
    private EditText mSearchEdit;
    private InputMethodManager mInputMethodManager;
    private Activity mActivity;
    private RecyclerView mSearchRank;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setUp() {
        if (isAdded()) {
            mActivity = getActivity();
        }
        if (mActivity != null) {
            mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_module_fragment_home, container, false);
        setUp();
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        mSearchBarLayout.setOnClickListener(mListener);
        mBack.setOnClickListener(mListener);
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.viewpager);
        mSearchContent = view.findViewById(R.id.search_content);
        mAppBarLayout = view.findViewById(R.id.app_bar_layout);
        mSearchBarLayout = view.findViewById(R.id.search);
        mBack = view.findViewById(R.id.back);
        mSearchEdit = view.findViewById(R.id.searc_edit);
        mSearchRank = view.findViewById(R.id.search_rank_recycler);

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

        mAdapter = new BaseTabFragmentPagerAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.search) {
                mAppBarLayout.setVisibility(View.GONE);
                mViewPager.setVisibility(View.GONE);
                mSearchContent.setVisibility(View.VISIBLE);
                if (mInputMethodManager != null) {
                    mSearchEdit.requestFocus();
                    mInputMethodManager.showSoftInput(mSearchEdit, 0);
                }
                List<SearchRankBO> list = SearchRankDAO.parseSearchRankData(mActivity);
                SearchRankAdapter rankAdapter = new SearchRankAdapter(mActivity,list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                mSearchRank.setLayoutManager(layoutManager);
                mSearchRank.setAdapter(rankAdapter);
            } else if (v.getId() == R.id.back) {
                mAppBarLayout.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.VISIBLE);
                mSearchContent.setVisibility(View.GONE);
                if (mInputMethodManager != null) {
                    mInputMethodManager.hideSoftInputFromWindow(mSearchEdit.getWindowToken(), 0);
                }
            }
        }
    };
}
