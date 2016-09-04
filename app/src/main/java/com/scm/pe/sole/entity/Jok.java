package com.scm.pe.sole.entity;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class Jok {

    private String content;

    private String hashId;

    private int unixtime;

    private String updatetime;

    private String url;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getHashId() {
        return this.hashId;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public int getUnixtime() {
        return this.unixtime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

}
