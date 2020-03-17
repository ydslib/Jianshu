package com.yds.mainmodule.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yds.mainmodule.R;
import com.yds.mainmodule.adapter.ArticleAdapter;
import com.yds.mainmodule.adapter.TestAdapter;
import com.yds.mainmodule.mobile.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/2/18.
 */
public class MakeListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private ArticleAdapter mAdapter;
    private List<Integer> mList;
    private int[] src = new int[]{
            R.drawable.test01,R.drawable.test02,R.drawable.test03,
            R.drawable.test04,R.drawable.test05,R.drawable.test06,
            R.drawable.test07,R.drawable.test08,R.drawable.test09,R.drawable.test10
    };
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
//        List<Integer> list = new ArrayList<>();
//        for (int i=0;i<20;i++){
//            list.add(i);
//        }
        mList = new ArrayList<>();
        for (int i=0;i<3;i++){
            mList.add(src[i]);
        }
        mAdapter = new ArticleAdapter(getActivity(),mList);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                for (int i=0;i<src.length;i++){
                    mList.add(src[i]);
                }
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        });

        mRefreshLayout.setColorSchemeResources(R.color.f_font_tabbar_text_selected);
    }
}
