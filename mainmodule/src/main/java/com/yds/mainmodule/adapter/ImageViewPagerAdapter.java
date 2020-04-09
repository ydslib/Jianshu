package com.yds.mainmodule.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.yds.jianshulib.widget.photoview.PhotoView;

import java.util.List;

/**
 * Created by yds
 * on 2020/4/8.
 */
public class ImageViewPagerAdapter<T> extends PagerAdapter {
    private List<T> mImageList;
    private Context mContext;

    public ImageViewPagerAdapter(List<T> mImageList, Context mContext) {
        this.mImageList = mImageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        T url = mImageList.get(position);
        PhotoView photoView = new PhotoView(mContext);
        Glide.with(mContext)
                .load(url)
                .into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).finish();
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
