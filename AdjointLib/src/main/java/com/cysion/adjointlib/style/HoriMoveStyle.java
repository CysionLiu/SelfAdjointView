package com.cysion.adjointlib.style;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.cysion.adjointlib.view.AdjointContainer;
import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.adjointlib.utils.ScreenUtil;
import com.cysion.adjointlib.view.AdjointImageView;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class HoriMoveStyle implements AdjointStyle {
    @Override
    public void onAttachedToImageView(AdjointContainer view) {

    }

    @Override
    public void onDetachedFromImageView(AdjointContainer view) {

    }

    @Override
    public void transform(AdjointContainer aContainer, Canvas canvas, int[] viewLocation, Rect parentLocation) {
        if (aContainer.getChildCount() != 1) {
            return;
        }
        if (aContainer.getChildAt(0) instanceof AdjointImageView) {
            ALog.single().ld("transform-begin");
            AdjointImageView childView = (AdjointImageView) aContainer.getChildAt(0);

            // image's width and height
            Drawable drawable = (childView).getDrawable();
            if (drawable==null) {
                return;
            }
            int iWidth = drawable.getIntrinsicWidth();
            int iHeight = drawable.getIntrinsicHeight();

            int x = viewLocation[0];
            int pLeft = parentLocation.left;
            int pRight = parentLocation.right;
            ALog.single().ld("parentLocation.right--" + pRight);

            if (iWidth <= 0 || iHeight <= 0) {
                return;
            }
            ALog.single().ld("iHeight--" + iHeight);

            // view's width and height
            int vWidth = aContainer.getWidth() - aContainer.getPaddingLeft() - aContainer.getPaddingRight();
            int vHeight = aContainer.getHeight() - aContainer.getPaddingTop() - aContainer.getPaddingBottom();

            // device's width
            int dWidth = ScreenUtil.getScreenWidth(aContainer.getContext());
            dWidth = dWidth < pRight ? dWidth : pRight;

            if (iWidth * vHeight > iHeight * vWidth || iWidth > vWidth) {
                // avoid over scroll
                if (x < pLeft ) {
                    x = pLeft;
                } else if (x > dWidth- vWidth) {
                    x = dWidth- vWidth;
                }
                x = x - pLeft;
                ALog.single().ld("target x:" + x);
                float imgScale = (float) vHeight / (float) iHeight;
                float imgMaxMoveScope = Math.abs((iWidth * imgScale - vWidth));
                int itemMaxMoveScope = pRight - pLeft - vWidth;
                float translateX = -(imgMaxMoveScope * x / itemMaxMoveScope);
                canvas.translate(translateX, 0);
            }
        }
    }
}
