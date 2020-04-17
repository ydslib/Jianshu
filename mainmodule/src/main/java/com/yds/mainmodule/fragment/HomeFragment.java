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

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yds.jianshulib.adapter.BaseTabFragmentPagerAdapter;
import com.yds.jianshulib.widget.SearchBarLayout;
import com.yds.jianshulib.widget.flowlayout.FlowLayout;
import com.yds.jianshulib.widget.flowlayout.TagAdapter;
import com.yds.jianshulib.widget.flowlayout.TagFlowLayout;
import com.yds.jianshulib.widget.flowlayout.TagView;
import com.yds.mainmodule.R;
import com.yds.mainmodule.adapter.SearchRankAdapter;
import com.yds.mainmodule.adapter.SearchResultAdapter;
import com.yds.mainmodule.bo.SearchRankBO;
import com.yds.mainmodule.bo.SearchResultListBO;
import com.yds.mainmodule.dao.SearchRankDAO;
import com.yds.mainmodule.dao.SearchResultListDAO;

import java.lang.ref.WeakReference;
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
    private TagView mTagView;
    private InputMethodManager mInputMethodManager;
    private Activity mActivity;
    private RecyclerView mSearchRank;
    private RecyclerView mSearchResult;

    private LinearLayout mLayoutHis;
    private LinearLayout mLayoutSearchResult;

    private TagFlowLayout mTagFlowLayout;
    private List<String> mHisSearchTagList;
    private TextView mHotTv;
    private TextView mRelateTv;
    private List<SearchResultListBO> mResultList;
    private SearchResultAdapter mResultAdapter;

    private String mSearchKey;

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
        mHotTv.setOnClickListener(mListener);
        mRelateTv.setOnClickListener(mListener);

        MyWathcer myWathcer = new MyWathcer();
        mSearchEdit.addTextChangedListener(myWathcer);

        mHisSearchTagList = new ArrayList<>();
        mHisSearchTagList.add("测试");
        mHisSearchTagList.add("管理");
        mHisSearchTagList.add("RecyclerView");

        mTagFlowLayout.setAdapter(new TagAdapter<String>(mHisSearchTagList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.flowlayout_adapter_text, parent, false);
                tv.setText(s);
                return tv;
            }
        });

        mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mTagView = (TagView) view;
                if (mTagView.isChecked()) {
                    mSearchEdit.setText(mHisSearchTagList.get(position));
                    mSearchEdit.setSelection(mHisSearchTagList.get(position).length());
                }
                return true;
            }
        });
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
        mSearchResult = view.findViewById(R.id.search_result_rv);
        mLayoutHis = view.findViewById(R.id.search_his);
        mLayoutSearchResult = view.findViewById(R.id.search_result_ll);
        mRelateTv = view.findViewById(R.id.relate_tv);
        mHotTv = view.findViewById(R.id.hot_tv);
        mTagFlowLayout = view.findViewById(R.id.tag_flow_layout);

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
                SearchRankAdapter rankAdapter = new SearchRankAdapter(mActivity, list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                mSearchRank.setLayoutManager(layoutManager);
                mSearchRank.setAdapter(rankAdapter);
            } else if ((v.getId() == R.id.back)) {
                mAppBarLayout.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.VISIBLE);
                mSearchContent.setVisibility(View.GONE);
                mSearchEdit.setText("");
                if (mInputMethodManager != null) {
                    mInputMethodManager.hideSoftInputFromWindow(mSearchEdit.getWindowToken(), 0);
                }
                if (mTagView != null) {
                    mTagView.setChecked(false);
                }
            } else if (v.getId() == R.id.relate_tv) {
                mHotTv.setTextColor(mActivity.getResources().getColor(R.color.f_text_gray));
                mRelateTv.setTextColor(mActivity.getResources().getColor(R.color.f_text_style));
                mResultList.clear();
                mResultList.addAll(SearchResultListDAO.parseSearchResultListData(mActivity, mSearchKey));
                mResultAdapter.notifyDataSetChanged();
            } else if (v.getId() == R.id.hot_tv) {
                mHotTv.setTextColor(mActivity.getResources().getColor(R.color.f_text_style));
                mRelateTv.setTextColor(mActivity.getResources().getColor(R.color.f_text_gray));
                mResultList.clear();
                mResultAdapter.notifyDataSetChanged();
            }
        }
    };

    private class MyWathcer implements TextWatcher {
        private WeakReference<HomeFragment> mWeakReference;

        private MyWathcer() {
            mWeakReference = new WeakReference<>(HomeFragment.this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            HomeFragment fragment = mWeakReference.get();
            mSearchKey = s.toString();
            if (!TextUtils.isEmpty(s.toString())) {
                fragment.mLayoutHis.setVisibility(View.GONE);
                fragment.mLayoutSearchResult.setVisibility(View.VISIBLE);
                mResultList = SearchResultListDAO.parseSearchResultListData(mActivity, s.toString());
                mResultAdapter = new SearchResultAdapter(mActivity, mResultList, s.toString());
                LinearLayoutManager manager = new LinearLayoutManager(mActivity);
                mSearchResult.setLayoutManager(manager);
                mSearchResult.setAdapter(mResultAdapter);
            } else {
                if (mTagView != null) {
                    mTagView.setChecked(false);
                }
                fragment.mLayoutHis.setVisibility(View.VISIBLE);
                fragment.mLayoutSearchResult.setVisibility(View.GONE);
            }

        }
    }
}
