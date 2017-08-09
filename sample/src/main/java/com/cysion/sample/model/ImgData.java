package com.cysion.sample.model;

/**
 * Created by Cysion Liu on 2017/8/9.
 */

public class ImgData extends BaseData {
    private String imgUrl;
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int aImgId) {
        imgId = aImgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String aImgUrl) {
        imgUrl = aImgUrl;
    }
}
