package com.yds.mainmodule.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private ArticleAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_module_fragment_make_list,container,false);
        mRecyclerView = view.findViewById(R.id.recyclerview);
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
        mAdapter = new ArticleAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

    }
}
