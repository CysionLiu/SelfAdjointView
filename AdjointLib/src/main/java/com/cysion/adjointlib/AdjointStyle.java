package com.cysion.adjointlib;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cysion.adjointlib.view.AdjointContainer;

/**
 * Created by Administrator on 2017/8/9.
 */

public interface AdjointStyle {
    void onAttachedToImageView(AdjointContainer view);

    void onDetachedFromImageView(AdjointContainer view);

    void transform(AdjointContainer aContainer, Canvas canvas, int[] itemLocation, Rect listRect);
}
