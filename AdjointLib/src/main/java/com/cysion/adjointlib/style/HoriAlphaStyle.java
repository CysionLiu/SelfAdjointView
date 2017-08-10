package com.cysion.adjointlib.style;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cysion.adjointlib.AdjConfig;
import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.adjointlib.utils.ScreenUtil;
import com.cysion.adjointlib.view.AdjointContainer;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class HoriAlphaStyle implements AdjointStyle {
    private float minAlpha = 0.5f;
    private float linearPos = 0.05f;
    private boolean linearable = false;
    private AdjConfig mAdjConfig;

    public HoriAlphaStyle setAdjConfig(AdjConfig aAdjConfig) {
        if (aAdjConfig == null) {
            return this;
        }
        setLinearable(aAdjConfig.isLinearable());
        setLinearPos(aAdjConfig.getLinearPos());
        setMinAlpha(aAdjConfig.getMin());
        return this;
    }

    public void setLinearPos(float aLinearPos) {
        if (aLinearPos > 0.2f || aLinearPos < 0.0f) {
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
        int x = viewLocation[0];
        int pLeft = parentLocation.left;
        int pRight = parentLocation.right;

        // view's width and height
        int vWidth = aContainer.getWidth() - aContainer.getPaddingLeft() - aContainer.getPaddingRight();

        // device's height
        int dWidth = ScreenUtil.getScreenWidth(aContainer.getContext());
        dWidth = dWidth < pRight ? dWidth : pRight;

        // avoid over scroll
        if (x < pLeft - vWidth) {
            x = pLeft - vWidth;
        } else if (x > dWidth) {
            x = dWidth;
        }
        x = x - pLeft;
        int itemMaxMoveScope = pRight - pLeft - vWidth;
        float index = x;
        if (index <= 0) {
            index = 1.0f;
        }
        if (index >= itemMaxMoveScope) {
            index = itemMaxMoveScope;
        }
        float al = 1.0f;
        ALog.single().ld("target x:" + x);
        if (linearable) {
            if (index < linearPos * itemMaxMoveScope) {
                index = 0;
            }
            al = (1 - minAlpha)*(itemMaxMoveScope-index) / itemMaxMoveScope + minAlpha;
        } else {
            al = (4 * minAlpha - 4.0f) * index * index / (itemMaxMoveScope * itemMaxMoveScope)
                    + (4.0f - 4 * minAlpha) * index / itemMaxMoveScope + minAlpha;
        }
        ALog.single().ld("target x:" + x);
        aContainer.setAlpha(al + minAlpha);
    }
}
