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

public class HoriScaleStyle implements AdjointStyle {

    private float minScale = 0.85f;
    private float multi = 1f;

    private float linearPos = 0.1f;
    private boolean linearable = false;

    public HoriScaleStyle setAdjConfig(AdjConfig aAdjConfig) {
        if (aAdjConfig == null) {
            return this;
        }
        setLinearable(aAdjConfig.isLinearable());
        setLinearPos(aAdjConfig.getLinearPos());
        setMinScale(aAdjConfig.getMin());
        setMulti(aAdjConfig.getMulti());
        return this;
    }

    public void setLinearPos(float aLinearPos) {
        if (aLinearPos > 0.3f || aLinearPos < 0.0f) {
            aLinearPos = 0.3f;
        }
        linearPos = aLinearPos;
    }

    public void setLinearable(boolean aLinearable) {
        linearable = aLinearable;
    }

    public void setMinScale(float aMinScale) {
        if (aMinScale < 0.7f || aMinScale >= 1.0f) {
            aMinScale = 0.7f;
        }
        minScale = aMinScale;
    }

    public void setMulti(float aMulti) {
        if (aMulti < 1.0f) {
            aMulti = 1.0f;
        }
        multi = aMulti;
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
        int vHeight = aContainer.getHeight() - aContainer.getPaddingTop() - aContainer.getPaddingBottom();
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
            al = (1 - minScale)*(itemMaxMoveScope-index) / itemMaxMoveScope + minScale;
        } else {
            al = (4 * minScale - 4.0f) * index * index / (itemMaxMoveScope * itemMaxMoveScope)
                    + (4.0f - 4 * minScale) * index / itemMaxMoveScope + minScale;
        }
        if (al < minScale) {
            al = minScale;
        }
        al = al * multi;
        canvas.scale(al, al, vWidth / 2, vHeight / 2);
    }
}
