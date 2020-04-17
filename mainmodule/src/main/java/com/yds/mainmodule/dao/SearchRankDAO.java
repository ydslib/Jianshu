package com.yds.mainmodule.dao;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yds.mainmodule.bo.SearchRankBO;
import com.yds.mainmodule.util.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/4/1.
 */
public class SearchRankDAO {
    private static final String FILE_NAME = "search.json";

    public static List<SearchRankBO> parseSearchRankData(Context context){
        List<SearchRankBO> list = new ArrayList<>();
        String result = AssetsUtils.getJson(context,FILE_NAME);
        JSONArray searchArray = JSON.parseArray(result);
        SearchRankBO bo;
        JSONObject obj;
        for (int i=0;i<searchArray.size();i++){
            bo = new SearchRankBO();
            obj = searchArray.getJSONObject(i);
            bo.setRankIndex(obj.getString("index"));
            bo.setSearchText(obj.getString("searchText"));
            bo.setReadNum(obj.getString("readNum"));
            list.add(bo);
        }
        return list;
    }
}
