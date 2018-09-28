package com.jason63.page;

/**
 * Created by Jasonli on 2018/9/28;
 */
public class Item {
    private String title, urlStr ;

    public Item(String title, String urlStr){
        this.title = title ;
        this.urlStr = urlStr ;
    }
    public String getTitle() {
        return title;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }
}

