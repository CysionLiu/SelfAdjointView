package com.cysion.adjointlib.style;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cysion.adjointlib.AdjConfig;
import com.cysion.adjointlib.view.AdjointContainer;
import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.adjointlib.utils.ScreenUtil;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class VerticalAlphaStyle implements AdjointStyle {
    private float minAlpha = 0.2f;
    private boolean linearable = true;
    private float linearPos = 0.05f;

    public VerticalAlphaStyle setAdjConfig(AdjConfig aAdjConfig) {
        if (aAdjConfig == null) {
            return this;
        }
        setLinearable(aAdjConfig.isLinearable());
        setLinearPos(aAdjConfig.getLinearPos());
        setMinAlpha(aAdjConfig.getMin());
        return this;
    }

    public void setLinearPos(float aLinearPos) {
        if (aLinearPos > 0.2f || aLinearPos <= 0.0f) {
            aLinearPos = 0.2f;
        }
        linearPos = aLinearPos;
    }

    public void setLinearable(boolean aLinearable) {
        linearable = aLinearable;
    }

    public void setMinAlpha(float aMinAlpha) {
        if (aMinAlpha < 0) {
            aMinAlpha = 0;
        }
        minAlpha = aMinAlpha;
    }

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
        if (linearable) {
            if (index < (linearPos * itemMaxMoveScope)) {
                index = 0;
            }
            al = (1 - minAlpha)*(itemMaxMoveScope-index) / itemMaxMoveScope + minAlpha;
        } else {
            al = (4 * minAlpha - 4.0f) * index * index / (itemMaxMoveScope * itemMaxMoveScope)
                    + (4.0f - 4 * minAlpha) * index / itemMaxMoveScope + minAlpha;
        }
        aContainer.setAlpha(al + minAlpha);
    }
}
