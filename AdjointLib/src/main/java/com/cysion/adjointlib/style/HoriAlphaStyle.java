package com.cysion.adjointlib.style;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cysion.adjointlib.AdjointContainer;
import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.adjointlib.utils.ScreenUtil;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class HoriAlphaStyle implements AdjointStyle {
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
        ALog.single().ld("target x:" + x);
        float al = -4.0f * index * index / (itemMaxMoveScope * itemMaxMoveScope) + 4.0f * index / itemMaxMoveScope;
        aContainer.setAlpha(al + 0.3f);
    }
}
