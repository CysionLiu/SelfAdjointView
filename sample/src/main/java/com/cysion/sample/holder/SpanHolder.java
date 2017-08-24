package com.cysion.sample.holder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysion.adjointlib.style.VerticalAlphaStyle;
import com.cysion.adjointlib.view.AdjointContainer;
import com.cysion.adjointlib.SimpleLocator;
import com.cysion.adjointlib.style.VerticalMoveStyle;
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
    private final TextView mContent;


    public SpanHolder(View itemView) {
        super(itemView);
        mImg = (ImageView) itemView.findViewById(R.id.img_holder_img);
        mCon = (AdjointContainer) itemView.findViewById(R.id.container);
        mContent = (TextView) itemView.findViewById(R.id.content);
        mCon.setLocator(SimpleLocator.single());
        mCon.addStyle(new VerticalMoveStyle());
        VerticalAlphaStyle style = new VerticalAlphaStyle();
        style.linearable(false);
        mCon.addStyle(style);
    }

    @Override
    public void bindData(Activity aActivity, List<? extends BaseData> aDataList, int position) {
        SpanData baseData = (SpanData) aDataList.get(position);
        mImg.setImageResource(baseData.getResId());
        mContent.setText(baseData.getTitle());
    }


}
