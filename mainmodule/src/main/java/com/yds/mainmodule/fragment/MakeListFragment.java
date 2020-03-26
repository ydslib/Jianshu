package com.yds.mainmodule.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yds.jianshulib.itf.OnLoadMoreListener;
import com.yds.mainmodule.R;
import com.yds.mainmodule.adapter.ArticleAdapter;
import com.yds.mainmodule.bo.MakeListDataBO;
import com.yds.mainmodule.dao.MakeListDataDAO;

import java.util.List;

/**
 * Created by yds
 * on 2020/2/18.
 */
public class MakeListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private ArticleAdapter mAdapter;
    List<MakeListDataBO> dataBOList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_module_fragment_make_list,container,false);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        initAdapter();
        return view;
    }

    private void initAdapter(){
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBOList = MakeListDataDAO.parseMakeListData(getContext());
        mAdapter = new ArticleAdapter(getActivity(),dataBOList);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataBOList.clear();
                dataBOList.addAll(MakeListDataDAO.refreshMakeListData(getContext()));
                mAdapter.notifyDataSetChanged();

                mRefreshLayout.setRefreshing(false);
            }
        });
        mRefreshLayout.setColorSchemeResources(R.color.f_font_tabbar_text_selected);

        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBOList.addAll(MakeListDataDAO.refreshMakeListData(getContext()));
                        mAdapter.notifyDataSetChanged();
                        if (mRefreshLayout.isRefreshing()){
                            mRefreshLayout.setRefreshing(false);
                        }
                    }
                },3000);

            }
        });
    }
}
