package com.cysion.sample.holder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cysion.sample.model.BaseData;

import java.util.List;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private String mPageType;
    protected Activity mActivity;

    public String getPageType() {
        return mPageType;
    }

    public void setPageType(String aPageType) {
        mPageType = aPageType;
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(Activity aActivity, List<? extends BaseData> aDataList, int position);
}
