package com.yds.mainmodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yds.jianshulib.widget.ThreeImageView;
import com.yds.mainmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/3/10.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mList = new ArrayList<>();
    private List<Integer> mList1 = new ArrayList<>();
    private List<Integer> mList2 = new ArrayList<>();
    private int[] src = new int[]{
            R.drawable.test01,R.drawable.test02,R.drawable.test03,
            R.drawable.test04,R.drawable.test05,R.drawable.test06,
            R.drawable.test07,R.drawable.test08,R.drawable.test09,R.drawable.test10
    };
    public ArticleAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i=0;i<4;i++){
            mList.add(src[i]);
        }
        for (int i=0;i<2;i++){
            mList1.add(src[i*2+2]);
        }
        for(int i=0;i<src.length;i++){
            mList2.add(src[i]);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position % 3 == 0) {
            holder.mImageView.setImagesData(mList);
        }else if(position % 3 == 1){
            holder.mImageView.setImagesData(mList1);
        }else{
            holder.mImageView.setImagesData(mList2);
        }

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
        private ThreeImageView mImageView;

        //        private RecyclerView mRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_three);
//            mRecyclerView = itemView.findViewById(R.id.image_recyclerview);
        }
    }
}
