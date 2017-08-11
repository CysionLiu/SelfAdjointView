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

public class VerticalAlphaStyle extends SimpleStyle implements AdjointStyle {

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
        float al = 1.0f;
        if (isLinearable()) {
            if (index < (getLinearPos() * itemMaxMoveScope)) {
                index = 0;
            }
            al = (1 - getMinAlpha())*(itemMaxMoveScope-index) / itemMaxMoveScope + getMinAlpha();
        } else {
            al = (4 * getMinAlpha() - 4.0f) * index * index / (itemMaxMoveScope * itemMaxMoveScope)
                    + (4.0f - 4 * getMinAlpha()) * index / itemMaxMoveScope + getMinAlpha();
        }
        aContainer.setAlpha(al);
    }
}
