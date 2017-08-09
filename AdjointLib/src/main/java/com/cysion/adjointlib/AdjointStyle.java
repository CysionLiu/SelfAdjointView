package com.cysion.adjointlib;

import android.graphics.Canvas;

import com.cysion.adjointlib.view.AdjointImageView;

/**
 * Created by Administrator on 2017/8/9.
 */

public interface AdjointStyle {
    void onAttachedToImageView(AdjointImageView view);

    void onDetachedFromImageView(AdjointImageView view);

    void transform(AdjointImageView view, Canvas canvas, int x, int y);
}
