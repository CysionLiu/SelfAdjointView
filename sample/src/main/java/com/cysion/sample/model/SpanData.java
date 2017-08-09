package com.cysion.sample.model;

/**
 * Created by Cysion Liu on 2017/8/9.
 */

public class SpanData extends BaseData {
    private String imgUrl;
    private String title;
    private String content;
    private int resId;

    public int getResId() {
        return resId;
    }

    public void setResId(int aResId) {
        resId = aResId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String aImgUrl) {
        imgUrl = aImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String aTitle) {
        title = aTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String aContent) {
        content = aContent;
    }
}
