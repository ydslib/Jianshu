package com.yds.mainmodule.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.yds.jianshulib.base.mvp.BaseActivity;
import com.yds.jianshulib.base.mvp.IBasePresenter;
import com.yds.jianshulib.utils.statusbar.StatusBarUtil;
import com.yds.mainmodule.R;
import com.yds.mainmodule.adapter.ImageViewPagerAdapter;

import java.util.List;

/**
 * Created by yds
 * on 2020/4/8.
 */
public class PhotoViewActivity extends BaseActivity {
    private ViewPager mImageViewPager;
    private TextView mCurImageNum;

    private int mCurrentPosition;
    private List<String> mImageList;

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo_view);
    }

    @Override
    protected void initViews() {
        mImageViewPager = findViewById(R.id.view_pager);
        mCurImageNum = findViewById(R.id.image_num);

        Intent intent = getIntent();
        mCurrentPosition = intent.getIntExtra("currentPosition", 0);
        mImageList = (List<String>) intent.getSerializableExtra("imageUrlList");
    }

    @Override
    protected void initEvents() {
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter<>(mImageList, this);
        mImageViewPager.setAdapter(adapter);

        mImageViewPager.setCurrentItem(mCurrentPosition, false);
        mCurImageNum.setText(mCurrentPosition + 1 + "/" + mImageList.size());
        mImageViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPosition = position;
                mCurImageNum.setText(mCurrentPosition + 1 + "/" + mImageList.size());
            }
        });
    }

    @Override
    protected IBasePresenter setPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }

}
