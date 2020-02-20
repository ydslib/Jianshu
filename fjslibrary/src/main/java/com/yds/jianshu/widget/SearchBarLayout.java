package com.yds.jianshu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.yds.jianshu.R;

/**
 * Created by yds
 * on 2020/2/19.
 */
public class SearchBarLayout extends LinearLayout {
    public SearchBarLayout(Context context) {
        super(context);
    }

    public SearchBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.f_search_layout,this);

    }

    public SearchBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
