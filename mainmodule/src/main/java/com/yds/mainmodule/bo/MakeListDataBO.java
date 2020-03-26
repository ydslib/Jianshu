package com.yds.mainmodule.bo;

import java.util.List;

/**
 * Created by yds
 * on 2020/3/24.
 */
public class MakeListDataBO {
    private String headUrl;
    private String userName;
    private String time;
    private String abstractStr;
    private List<String> mList;
    private String jevelNum;
    private String readNum;
    private String commentNum;
    private String awesomeNum;

    public String getJevelNum() {
        return jevelNum;
    }

    public void setJevelNum(String jevelNum) {
        this.jevelNum = jevelNum;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getAwesomeNum() {
        return awesomeNum;
    }

    public void setAwesomeNum(String awesomeNum) {
        this.awesomeNum = awesomeNum;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAbstractStr() {
        return abstractStr;
    }

    public void setAbstractStr(String abstractStr) {
        this.abstractStr = abstractStr;
    }

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }
}
