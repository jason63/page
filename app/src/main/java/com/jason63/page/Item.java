package com.jason63.page;

/**
 * Created by Jasonli on 2018/9/28;
 */
public class Item {
    private String title, urlStr, body ;

    public Item(String title, String urlStr, String body){
        this.title = title ;
        this.urlStr = urlStr ;
        if(!body.equals(""))
        this.body = body ;
        else this.body = "no further descriptions. (¦3[▓▓] " ;
    }
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

