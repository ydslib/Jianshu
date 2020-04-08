package com.yds.mainmodule.bo;

/**
 * Created by yds
 * on 2020/4/8.
 */
public class SearchResultListBO {
    private String name;
    private String content;
    private String author;
    private String readNum;
    private String commentNum;
    private String awesomeNum;
    private int hot;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

}
