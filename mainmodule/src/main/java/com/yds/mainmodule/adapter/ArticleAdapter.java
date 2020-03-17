package com.yds.mainmodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yds.jianshulib.widget.MultiImageView;
import com.yds.mainmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/3/10.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mList;
    public ArticleAdapter(Context mContext,List<Integer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mImageView.setImagesData(mList);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//        DividerItemDecoration decoration = new DividerItemDecoration(mContext,LinearLayout.HORIZONTAL);
//        holder.mRecyclerView.addItemDecoration(decoration);
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        holder.mRecyclerView.setLayoutManager(layoutManager);
//        ImageAdapter adapter = new ImageAdapter(mContext);
//        holder.mRecyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MultiImageView mImageView;

        //        private RecyclerView mRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_three);
//            mRecyclerView = itemView.findViewById(R.id.image_recyclerview);
        }
    }
}
