package com.yds.mainmodule.dao;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yds.mainmodule.bo.SearchResultListBO;
import com.yds.mainmodule.util.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/4/8.
 */
public class SearchResultListDAO {
    private static final String FILE_NAME = "search1.json";
    public static List<SearchResultListBO> parseSearchResultListData(Context context,String searchKey){
        List<SearchResultListBO> list = new ArrayList<>();
        String result = AssetsUtils.getJson(context,FILE_NAME);
        JSONArray searchArray = JSON.parseArray(result);
        String name;
        JSONObject obj;
        SearchResultListBO bo;
        for(int i=0;i<searchArray.size();i++){
            bo = new SearchResultListBO();
            obj = searchArray.getJSONObject(i);
            name = obj.getString("name");
            if (name.contains(searchKey)){
                bo.setName(name);
                bo.setContent(obj.getString("content"));
                bo.setAuthor(obj.getString("author"));
                bo.setReadNum(obj.getString("read_num"));
                bo.setCommentNum(obj.getString("comment_num"));
                bo.setAwesomeNum(obj.getString("awesome_num"));
                bo.setHot(obj.getInteger("hot"));
                bo.setTime(obj.getString("time"));
                list.add(bo);
            }
        }
        return list;
    }
}
