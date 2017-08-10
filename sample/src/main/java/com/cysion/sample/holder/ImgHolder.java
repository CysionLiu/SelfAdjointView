package com.cysion.sample.holder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.cysion.adjointlib.AdjointContainer;
import com.cysion.adjointlib.SimpleLocator;
import com.cysion.adjointlib.style.HoriMoveStyle;
import com.cysion.sample.R;
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
        mCon.setLocator(SimpleLocator.single());
    }

    @Override
    public void bindData(Activity aActivity, List<? extends BaseData> aDataList, int position) {
        ImgData baseData = (ImgData) aDataList.get(position);
        if (baseData.getOriType() > 1) {
            mCon.setAdjointStyle(new HoriMoveStyle());
        }
        mImg.setImageResource(baseData.getImgId());

    }
}
