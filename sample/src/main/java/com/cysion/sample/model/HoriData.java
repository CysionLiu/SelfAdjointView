package com.cysion.sample.model;

import java.util.List;

/**
 * Created by Cysion Liu on 2017/8/9.
 */

public class HoriData extends BaseData {
    private List<? extends BaseData> dataList;

    public List<? extends BaseData> getDataList() {
        return dataList;
    }

    public void setDataList(List<? extends BaseData> aDataList) {
        dataList = aDataList;
    }
}
