package com.cysion.sample.holder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.cysion.adjointlib.SimpleLocator;
import com.cysion.adjointlib.style.HoriAlphaStyle;
import com.cysion.adjointlib.style.HoriMoveStyle;
import com.cysion.adjointlib.style.HoriScaleStyle;
import com.cysion.adjointlib.style.VerticalAlphaStyle;
import com.cysion.adjointlib.style.VerticalMoveStyle;
import com.cysion.adjointlib.view.AdjointContainer;
import com.cysion.sample.R;
import com.cysion.sample.helper.HoriLocator;
import com.cysion.sample.model.BaseData;
import com.cysion.sample.model.ImgData;

import java.util.List;

/**
 * Created by cysionliu on 2017/8/9.
 */

public class ImgHolder extends BaseViewHolder {

    private final ImageView mImg;
    private final AdjointContainer mCon;

    public ImgHolder(View itemView) {
        super(itemView);
        mImg = (ImageView) itemView.findViewById(R.id.img_holder_img);
        mCon = (AdjointContainer) itemView.findViewById(R.id.container);

    }

    @Override
    public void bindData(Activity aActivity, List<? extends BaseData> aDataList, int position) {
        ImgData baseData = (ImgData) aDataList.get(position);
        mCon.clearStyles();
        if (baseData.getOriType() > 1) {
            mCon.setLocator(HoriLocator.single());
            mCon.addStyle(new HoriMoveStyle());
        } else if (baseData.getOriType() == 1) {
            mCon.setLocator(HoriLocator.single());
            if (position<aDataList.size()-1) {
                mCon.addStyle(new HoriScaleStyle());
                mCon.addStyle(new HoriAlphaStyle());
            }
        } else {
            mCon.setLocator(SimpleLocator.single());
            mCon.addStyle(new VerticalMoveStyle());
            mCon.addStyle(new VerticalAlphaStyle());
        }
        mImg.setImageResource(baseData.getImgId());

    }
}
