package com.cysion.sample;

import com.cysion.sample.adapter.GlobalAdapter;
import com.cysion.sample.model.BaseData;
import com.cysion.sample.model.HoriData;
import com.cysion.sample.model.ImgData;
import com.cysion.sample.model.SpanData;

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

    public int[] imgIds = {R.mipmap.p1c, R.mipmap.p2c, R.mipmap.p3c, R.mipmap.p4c, R.mipmap.p5c, R.mipmap.p6c};

    public List<BaseData> getImgs() {
        List<BaseData> list = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImgData data = new ImgData();
            data.setItemType(GlobalAdapter.IMAGE);
            int temp = 0;
            temp = i;
            if (i >= imgIds.length) {
                temp = i % imgIds.length;
            }
            data.setImgId(imgIds[temp]);
            list.add(data);
        }
        return list;
    }

    public List<BaseData> getSpans() {
        List<BaseData> list = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            SpanData data = new SpanData();
            data.setItemType(GlobalAdapter.SPAN);
            data.setTitle("Adjoint=" + i);
            data.setContent("ImageView=" + i);
            data.setResId(imgIds[i]);
            list.add(data);
        }
        return list;
    }

    public BaseData getHori1() {
        HoriData horiData = new HoriData();
        horiData.setItemType(GlobalAdapter.HORI_LIST);
        List<BaseData> temp = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImgData data = new ImgData();
            data.setItemType(GlobalAdapter.HORI_IMG1);
            data.setOriType(1);
            data.setImgId(imgIds[i]);
            temp.add(data);
        }
        horiData.setDataList(temp);
        return horiData;
    }
    public BaseData getHori2() {
        HoriData horiData = new HoriData();
        horiData.setItemType(GlobalAdapter.HORI_LIST);
        List<BaseData> temp = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImgData data = new ImgData();
            data.setItemType(GlobalAdapter.HORI_IMG2);
            data.setOriType(2);
            data.setImgId(imgIds[i]);
            temp.add(data);
        }
        horiData.setDataList(temp);
        return horiData;
    }

}
