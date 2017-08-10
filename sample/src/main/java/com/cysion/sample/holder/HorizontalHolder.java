package com.cysion.sample.holder;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cysion.sample.R;
import com.cysion.sample.adapter.GlobalAdapter;
import com.cysion.sample.helper.HoriLocator;
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
    private Rect mRect = new Rect();

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
        mAdapter = new GlobalAdapter(itemView.getContext(), mBaseDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.getGlobalVisibleRect(mRect);
                HoriLocator.single().setParentFrame(mRect);
            }
        });
    }
}
