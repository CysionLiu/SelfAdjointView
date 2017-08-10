package com.cysion.sample.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cysion.sample.R;
import com.cysion.sample.holder.BaseViewHolder;
import com.cysion.sample.holder.HorizontalHolder;
import com.cysion.sample.holder.ImgHolder;
import com.cysion.sample.holder.SpanHolder;
import com.cysion.sample.model.BaseData;

import java.util.List;

public class GlobalAdapter extends RecyclerView.Adapter {

    //layout type
    public static final int IMAGE = 0XC01;
    public static final int SPAN = 0XC02;
    public static final int HORI_LIST = 0XC03;
    public static final int HORI_IMG = 0XC04;
    //--
    private List<? extends BaseData> mDataList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Activity mActivity;

    public GlobalAdapter(Context aContext, List<? extends BaseData> aDataList) {
        if (aContext instanceof Activity) {
            mActivity = (Activity) aContext;
        }
        mContext = aContext.getApplicationContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        mDataList = aDataList;
    }

    //根据type分发显示各个holder对应的UI
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case IMAGE:
                return new ImgHolder(mLayoutInflater.inflate(R.layout
                        .holder_img, parent, false));
            case HORI_IMG:
                return new ImgHolder(mLayoutInflater.inflate(R.layout
                        .holder_inner_img, parent, false));
            case SPAN:
                return new SpanHolder(mLayoutInflater.inflate(R.layout
                        .holder_span, parent, false));
            case HORI_LIST:
                return new HorizontalHolder(mLayoutInflater.inflate(R.layout
                        .holder_hori, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder) holder).bindData(mActivity, mDataList, holder.getLayoutPosition());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();

    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemType();
    }
}
