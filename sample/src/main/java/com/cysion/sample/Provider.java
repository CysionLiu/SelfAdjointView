package com.cysion.sample;

import com.cysion.sample.adapter.GlobalAdapter;
import com.cysion.sample.model.ImgData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cysion Liu on 2017/8/9.
 */

public class Provider {

    private static volatile Provider instance = new Provider();

    private Provider() {

    }

    public static synchronized Provider single() {
        return instance;
    }

    public int[] imgIds = {R.mipmap.p1c, R.mipmap.p2c, R.mipmap.p3c, R.mipmap.p4c, R.mipmap.p5c,R.mipmap.p6c};

    public List<ImgData> getImgs() {
        List<ImgData> list = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImgData data = new ImgData();
            data.setItemType(GlobalAdapter.IMAGE);
            data.setImgId(imgIds[i]);
            list.add(data);
        }
        return list;
    }

}
