package com.yds.jianshulib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/3/30.
 */
public class FlowLayout extends ViewGroup {
    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mLineHeightList = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("onLayout-Time:","onLayout");
        mAllViews.clear();
        mLineHeightList.clear();
        int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (childWidth + mlp.leftMargin + mlp.rightMargin + lineWidth > width) {
                mLineHeightList.add(lineHeight);
                mAllViews.add(lineViews);
                lineWidth = 0;
                lineViews = new ArrayList<>();
            }
            lineWidth+=childWidth+mlp.leftMargin+mlp.rightMargin;
            lineHeight=Math.max(lineHeight,childHeight+mlp.topMargin+mlp.bottomMargin);
            lineViews.add(child);
        }
        mLineHeightList.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        int lineNums = mAllViews.size();
        for (int i=0;i<lineNums;i++){
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeightList.get(i);
            for (int j=0;j<lineViews.size();j++){
                View child = lineViews.get(j);
                if (child.getVisibility()==View.GONE){
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left+lp.leftMargin;
                int tc = top+lp.topMargin;
                int rc = lc+child.getMeasuredWidth();
                int bc = tc+child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);

                left+=child.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
            }
            left = 0;
            top+=lineHeight;
        }

    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("onMeasure-Time:","onMeasure");
        //获取总宽度（父容器为它设置的测量值）
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        //获取总高度（父容器为它设置的测量值）
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        //获取父容器为它设置的测量模式
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        //获取父容器为它设置的测量模式
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //如果是wrap_content情况，记录宽和高
        int width = 0;
        int height = 0;

        //记录每一行的宽度，width不断获取最大宽度
        int lineWidth = 0;
        //每一行的高度，累加至height
        int lineHeight = 0;
        int count = getChildCount();
        Log.d("onMeasure-Time:","count="+count);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            //测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //当前子控件实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            //当前子控件实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            //如果加入当前child超出了最大宽度，则将得到的目前最大宽度给width,累加height，然后开启新行
            if (lineWidth + childWidth > sizeWidth) {
                width = Math.max(lineWidth, childWidth);//取最大
                lineWidth = childWidth;//重新开启新行，开始记录
                height += lineHeight;
                lineHeight = childHeight;
            } else {//否则累加到lineWidth，lineHeigth取最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            //如果是最后一个，则将当前记录的最大宽度和当前的lineWidth做比较
            if (i == count - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width,
                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }
}
