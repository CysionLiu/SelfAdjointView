package com.cysion.sample.holder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysion.adjointlib.AdjointContainer;
import com.cysion.sample.R;
import com.cysion.sample.model.BaseData;
import com.cysion.sample.model.SpanData;

import java.util.List;

/**
 * Created by cysionliu on 2017/8/9.
 */

public class SpanHolder extends BaseViewHolder {
    private final ImageView mImg;
    private final AdjointContainer mCon;
    private final TextView mTitle;
    private final TextView mContent;

    public SpanHolder(View itemView) {
        super(itemView);
        mImg = (ImageView) itemView.findViewById(R.id.img_holder_img);
        mCon = (AdjointContainer) itemView.findViewById(R.id.container);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mContent = (TextView) itemView.findViewById(R.id.content);
        mCon.setImageView(mImg);
    }

    @Override
    public void bindData(Activity aActivity, List<? extends BaseData> aDataList, int position) {
        SpanData baseData = (SpanData) aDataList.get(position);
        mImg.setImageResource(baseData.getResId());
        mCon.setParentLocation(getParentLocation());
        mTitle.setText(baseData.getTitle());
        mContent.setText(baseData.getContent());
    }


}
