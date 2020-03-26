package com.yds.mainmodule.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yds.jianshulib.itf.OnLoadMoreListener;
import com.yds.jianshulib.widget.CirCleImageView;
import com.yds.jianshulib.widget.MultiImageView;
import com.yds.mainmodule.R;
import com.yds.mainmodule.bo.MakeListDataBO;

import java.util.List;

/**
 * Created by yds
 * on 2020/3/10.
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_FOOTER = 1;
    private List<MakeListDataBO> mDataBOList;

    public ArticleAdapter(Context mContext, List<MakeListDataBO> dataBOList) {
        this.mContext = mContext;
        this.mDataBOList = dataBOList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_article, parent, false);
//        return new ViewHolder(view);
        if (viewType == TYPE_CONTENT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_article, parent, false);
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_footer, parent, false);
            return new FootViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_FOOTER){
            FootViewHolder viewHolder = (FootViewHolder) holder;
            viewHolder.mProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.f_font_tabbar_text_selected), PorterDuff.Mode.MULTIPLY);
        }else{
            ViewHolder viewHolder = (ViewHolder) holder;
            Glide.with(mContext).load(mDataBOList.get(position).getHeadUrl()).thumbnail(0.3f).into(viewHolder.mCircleImageView);
            viewHolder.mUserName.setText(mDataBOList.get(position).getUserName());
            viewHolder.mTime.setText(mDataBOList.get(position).getTime());
            viewHolder.mAbstract.setText(mDataBOList.get(position).getAbstractStr());
            List<String> mList = mDataBOList.get(position).getList();
            viewHolder.mImageView.setImagesData(mList);
        }


    }

    @Override
    public int getItemCount() {
        return mDataBOList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mDataBOList.size()&& OnLoadMoreListener.isAllScreen()) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MultiImageView mImageView;
        private CirCleImageView mCircleImageView;
        private TextView mUserName;
        private TextView mTime;
        private TextView mAbstract;

        //        private RecyclerView mRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_three);
            mCircleImageView = itemView.findViewById(R.id.circle_img);
            mUserName = itemView.findViewById(R.id.user_name);
            mTime = itemView.findViewById(R.id.time);
            mAbstract = itemView.findViewById(R.id.abstract_article_tv);
//            mRecyclerView = itemView.findViewById(R.id.image_recyclerview);
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        private ContentLoadingProgressBar mProgressBar;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.load_more_progress);
        }
    }
}
