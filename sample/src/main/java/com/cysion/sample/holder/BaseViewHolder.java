package com.cysion.sample.holder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    public abstract void bindData(Activity aActivity, Object obj, int position);
}
