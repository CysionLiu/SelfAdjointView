package com.cysion.adjointlib.style;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cysion.adjointlib.view.AdjointContainer;
import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.adjointlib.utils.ScreenUtil;
import com.cysion.adjointlib.view.AdjointImageView;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class VerticalMoveStyle implements AdjointStyle {
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

            int y = viewLocation[1];
            int ptop = parentLocation.top;
            int pbottom = parentLocation.bottom;
            ALog.single().ld("parentLocation.bottom--" + parentLocation.bottom);

            if (iWidth <= 0 || iHeight <= 0) {
                return;
            }
            ALog.single().ld("iHeight--" + iHeight);
            Log.e("flag--", "transform(VerticalMoveStyle.java:46)-->>" + iHeight);
            // view's width and height
            int vWidth = aContainer.getWidth() - aContainer.getPaddingLeft() - aContainer.getPaddingRight();
            int vHeight = aContainer.getHeight() - aContainer.getPaddingTop() - aContainer.getPaddingBottom();

            // device's height
            int dHeight = ScreenUtil.getScreenHeight(aContainer.getContext());
            dHeight = dHeight < pbottom ? dHeight : pbottom;

            if (iWidth * vHeight < iHeight * vWidth || iHeight > vHeight) {
                // avoid over scroll
                if (y < ptop) {
                    y = ptop;
                } else if (y > dHeight - vHeight) {
                    y = dHeight - vHeight;
                }
                y = y - ptop;
                ALog.single().ld("target y:" + y);
                float imgScale = (float) vWidth / (float) iWidth;
                float imgMaxMoveScope = Math.abs((iHeight * imgScale - vHeight));
                int itemMaxMoveScope = pbottom - ptop - vHeight;
                float translateY = -(imgMaxMoveScope * y / itemMaxMoveScope);
                canvas.translate(0, translateY);
            }
        }
    }
}
