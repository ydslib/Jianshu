package com.yds.mainmodule.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yds.mainmodule.R;
import com.yds.mainmodule.bo.SearchRankBO;


import java.util.List;

/**
 * Created by yds
 * on 2020/4/1.
 */
public class SearchRankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<SearchRankBO> mSearchDataList;

    public SearchRankAdapter(Context mContext, List<SearchRankBO> searchList) {
        this.mContext = mContext;
        this.mSearchDataList = searchList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_search_rank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.rankIndex.setText(mSearchDataList.get(position).getRankIndex());
        String seachText = mSearchDataList.get(position).getSearchText();
        String readTip = "阅读 " + mSearchDataList.get(position).getReadNum();
        SpannableStringBuilder searchContent = new SpannableStringBuilder(seachText + readTip);
        ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.f_text_gray));
        searchContent.setSpan(span,seachText.length(),seachText.length()+readTip.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(14,true);
        searchContent.setSpan(sizeSpan,seachText.length(),seachText.length()+readTip.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.searchText.setText(searchContent);
    }

    @Override
    public int getItemCount() {
        return mSearchDataList.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rankIndex;
        private TextView searchText;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rankIndex = itemView.findViewById(R.id.rank_index);
            searchText = itemView.findViewById(R.id.search_text);
        }
    }
}
