package com.yds.jianshulib.itf;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by yds
 * on 2020/3/24.
 */
public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private int countItem;
    private int lastItem;
    private boolean isScrolled = false;
    private RecyclerView.LayoutManager mLayoutManager;

    protected abstract void onLoading(int countItem,int lastItem);

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING ||newState == RecyclerView.SCROLL_STATE_SETTLING){
            isScrolled = true;
        }else{
            isScrolled = false;
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            mLayoutManager = recyclerView.getLayoutManager();
            countItem = mLayoutManager.getItemCount();
            lastItem = ((LinearLayoutManager)mLayoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (isScrolled&&countItem!=lastItem&&lastItem==countItem-1){
            onLoading(countItem,lastItem);
        }
    }
}
