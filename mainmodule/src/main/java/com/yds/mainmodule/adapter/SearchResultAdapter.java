package com.yds.mainmodule.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yds.mainmodule.R;
import com.yds.mainmodule.bo.SearchResultListBO;

import java.util.List;

/**
 * Created by yds
 * on 2020/4/8.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SearchResultListBO> list;
    private Context mContext;
    private String mSearchKey;

    public SearchResultAdapter(Context context, List<SearchResultListBO> list, String searchKey) {
        this.mContext = context;
        this.list = list;
        this.mSearchKey = searchKey;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapter_item_search_result, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        SearchResultListBO bo = list.get(position);
        if (bo != null) {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            String name = bo.getName();
            if (name.length() >= 18) {
                builder.append(name.substring(0, 18) + "......");
            }else{
                builder.append(name);
            }
            int index = name.indexOf(mSearchKey);
            ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.f_text_style));
            builder.setSpan(span, index, index + mSearchKey.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.articleName.setText(builder);

            builder = new SpannableStringBuilder();
            String content = bo.getContent();
            builder.append(content);
            index = content.indexOf(mSearchKey);
            builder.setSpan(span, index, index + mSearchKey.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.articleContent.setText(builder);

            viewHolder.articleAuthor.setText(bo.getAuthor());
            int readNum = Integer.parseInt(bo.getReadNum());
            if (readNum / 1000 > 0) {
                viewHolder.articleReadNum.setText(readNum / 1000.0 + " 阅读");
            } else {
                viewHolder.articleReadNum.setText(readNum + " 阅读");
            }

            viewHolder.articleCommentNum.setText(bo.getCommentNum() + " 评论");
            viewHolder.articleAwesomeNum.setText(bo.getAwesomeNum() + " 赞");
            viewHolder.articleTime.setText(bo.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView articleName;
        private TextView articleContent;
        private TextView articleAuthor;
        private TextView articleReadNum;
        private TextView articleCommentNum;
        private TextView articleAwesomeNum;
        private TextView articleTime;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            articleName = itemView.findViewById(R.id.article_name);
            articleContent = itemView.findViewById(R.id.article_content);
            articleAuthor = itemView.findViewById(R.id.article_author);
            articleReadNum = itemView.findViewById(R.id.article_read);
            articleCommentNum = itemView.findViewById(R.id.article_comment);
            articleAwesomeNum = itemView.findViewById(R.id.article_awesome);
            articleTime = itemView.findViewById(R.id.article_time);
        }
    }
}
