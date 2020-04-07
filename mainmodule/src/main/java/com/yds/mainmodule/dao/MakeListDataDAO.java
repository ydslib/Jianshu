package com.yds.mainmodule.dao;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yds.mainmodule.bo.MakeListDataBO;
import com.yds.mainmodule.util.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/3/24.
 */
public class MakeListDataDAO {
    private final static String FILE_NAME = "makelist.json";

    public static List<MakeListDataBO> refreshMakeListData(Context context) {
        List<MakeListDataBO> dataBOList = parseMakeListData(context);
        List<MakeListDataBO> refreshDataBOList = new ArrayList<>();
        for (int i = dataBOList.size() - 1; i >= 0; i--) {
            refreshDataBOList.add(dataBOList.get(i));
        }
        return refreshDataBOList;
    }

    /**
     * @param context 上下文
     * @return List<MakeListDataBO>
     */
    public static List<MakeListDataBO> parseMakeListData(Context context) {
        List<MakeListDataBO> dataBOList = new ArrayList<>();
        String result = AssetsUtils.getJson(context, FILE_NAME);
        JSONArray array = JSON.parseArray(result);
        for (int j = 0; j < array.size(); j++) {
            MakeListDataBO dataBO = new MakeListDataBO();
            JSONObject obj = array.getJSONObject(j);
            dataBO.setHeadUrl(obj.getString("head_url"));
            dataBO.setUserName(obj.getString("name"));
            dataBO.setTime(obj.getString("time"));
            dataBO.setAbstractStr(obj.getString("abstract"));
            int awesome = obj.getInteger("awesome_num");
            dataBO.setAwesomeNum(awesome >= 1000 ? awesome / 1000.0f + "k" : String.valueOf(awesome));
            int readNum = obj.getInteger("read_num");
            dataBO.setReadNum(readNum >= 1000 ? readNum / 1000.0f + "k" : String.valueOf(readNum));
            dataBO.setJevelNum(obj.getString("jevel_num"));
            int commentNum = obj.getInteger("comment_num");
            dataBO.setCommentNum(commentNum >= 1000 ? commentNum / 1000.0f + "k" : String.valueOf(commentNum));
            JSONArray jsonArray = obj.getJSONArray("image_list");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject imgObj = jsonArray.getJSONObject(i);
                list.add(imgObj.getString("image_url"));
            }
            dataBO.setList(list);
            dataBOList.add(dataBO);
        }

        return dataBOList;
    }


}
