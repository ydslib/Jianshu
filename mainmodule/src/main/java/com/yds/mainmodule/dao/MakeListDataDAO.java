package com.yds.mainmodule.dao;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yds.mainmodule.bo.MakeListDataBO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yds
 * on 2020/3/24.
 */
public class MakeListDataDAO {
    private final static String FILE_NAME = "makelist.json";

    public static List<MakeListDataBO> refreshMakeListData(Context context){
        List<MakeListDataBO> dataBOList = parseMakeListData(context);
        List<MakeListDataBO> refreshDataBOList = new ArrayList<>();
        for (int i=dataBOList.size()-1;i>=0;i--){
            refreshDataBOList.add(dataBOList.get(i));
        }
        return refreshDataBOList;
    }

    /**
     *
     * @param context
     * @return
     */
    public static List<MakeListDataBO> parseMakeListData(Context context) {
        List<MakeListDataBO> dataBOList = new ArrayList<>();
        String result = getJson(context);
        JSONArray array = JSON.parseArray(result);
        for (int j=0;j<array.size();j++){
            MakeListDataBO dataBO = new MakeListDataBO();
            JSONObject obj = array.getJSONObject(j);
            dataBO.setHeadUrl(obj.getString("head_url"));
            dataBO.setUserName(obj.getString("name"));
            dataBO.setTime(obj.getString("time"));
            dataBO.setAbstractStr(obj.getString("abstract"));
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

    public static String getJson(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        InputStreamReader isr = null;
        BufferedReader bf = null;
        try {
            isr = new InputStreamReader(assetManager.open(FILE_NAME));
            bf = new BufferedReader(isr);
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
