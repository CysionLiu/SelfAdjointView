package com.cysion.adjointlib.style;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cysion.adjointlib.SimpleStyle;
import com.cysion.adjointlib.view.AdjointContainer;
import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.adjointlib.utils.ScreenUtil;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class VerticalScaleStyle extends SimpleStyle implements AdjointStyle {

    @Override
    public void onAttachedToImageView(AdjointContainer view) {

    }

    @Override
    public void onDetachedFromImageView(AdjointContainer view) {

    }

    @Override
    public void transform(AdjointContainer aContainer, Canvas canvas, int[] viewLocation, Rect parentLocation) {
        ALog.single().ld("alpha-begin");
        int y = viewLocation[1];
        int ptop = parentLocation.top;
        int pbottom = parentLocation.bottom;
        ALog.single().ld("parentLocation.bottom--" + parentLocation.bottom);

        // view's width and height
        int vWidth = aContainer.getWidth() - aContainer.getPaddingLeft() - aContainer.getPaddingRight();
        int vHeight = aContainer.getHeight() - aContainer.getPaddingTop() - aContainer.getPaddingBottom();

        // device's height
        int dHeight = ScreenUtil.getScreenHeight(aContainer.getContext());
        dHeight = dHeight < pbottom ? dHeight : pbottom;

        // avoid over scroll
        if (y < ptop - vHeight) {
            y = ptop - vHeight;
        } else if (y > dHeight) {
            y = dHeight;
        }
        y = y - ptop;
        int itemMaxMoveScope = pbottom - ptop - vHeight;
        float index = y;
        if (index <= 0) {
            index = 1.0f;
        }
        if (index >= itemMaxMoveScope) {
            index = itemMaxMoveScope;
        }
        ALog.single().ld("target y:" + y);
        float al = 1.0f;
        if (isLinearable()) {
            if (index < getLinearPos() * itemMaxMoveScope) {
                index = 0;
            }
            al = (1 - getMinScale()) * (itemMaxMoveScope - index) / itemMaxMoveScope + getMinScale();
        } else {
            if(index>0.4f*itemMaxMoveScope&&index<0.6f*itemMaxMoveScope) {
                index = 0.5f * itemMaxMoveScope;
                mReachCallBack.reachMiddle(aContainer);
            }
            al = (4 * getMinScale() - 4.0f) * index * index / (itemMaxMoveScope * itemMaxMoveScope)
                    + (4.0f - 4 * getMinScale()) * index / itemMaxMoveScope + getMinScale();
        }
        if (al < getMinScale()) {
            al = getMinScale();
        }
        al = al * getFactor();
        canvas.scale(al, al, vWidth/2, vHeight*getPrivotY());
    }
}
