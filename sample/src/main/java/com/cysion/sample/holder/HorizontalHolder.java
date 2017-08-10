package com.cysion.sample.holder;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cysion.sample.R;
import com.cysion.sample.adapter.GlobalAdapter;
import com.cysion.sample.model.BaseData;
import com.cysion.sample.model.HoriData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cysionliu on 2017/8/9.
 */

public class HorizontalHolder extends BaseViewHolder {

    private final RecyclerView mRecyclerView;
    private List<BaseData> mBaseDatas = new ArrayList<>();
    private GlobalAdapter mAdapter;

    public HorizontalHolder(View itemView) {
        super(itemView);
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_holder);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));


    }

    @Override
    public void bindData(Activity aActivity, List<? extends BaseData> aDataList, int position) {
        HoriData data = (HoriData) aDataList.get(position);
        if (data == null) {
            return;
        }
        mBaseDatas.clear();
        mBaseDatas.addAll(data.getDataList());
        Log.e("flag--", "bindData(HorizontalHolder.java:43)-->>" + mBaseDatas.size());
        mAdapter = new GlobalAdapter(itemView.getContext(), mBaseDatas);
        mRecyclerView.setAdapter(mAdapter);
//        if (mAdapter == null) {
//            Log.e("flag--","bindData(HorizontalHolder.java:46)-->>");
//            mAdapter = new GlobalAdapter(itemView.getContext(), mBaseDatas);
//            mRecyclerView.setAdapter(mAdapter);
//        } else {
//            Log.e("flag--","bindData(HorizontalHolder.java:50)-->>");
//            mAdapter.notifyDataSetChanged();
//        }
    }
}
