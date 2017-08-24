package com.cysion.adjointlib;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.cysion.adjointlib.view.AdjointContainer;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public abstract class SimpleStyle implements AdjointStyle {
    private float minAlpha = 0.5f;
    private float minScale = 0.85f;
    private float linearPos = 0.20f;
    private boolean linearable = false;
    private float factor = 1.0f;
    private float privotX = 1.0f;
    private float privotY = 1.0f;
    protected AdjointContainer.OnReachMiddleCallBack mReachCallBack = new AdjointContainer.OnReachMiddleCallBack() {
        @Override
        public void reachMiddle(AdjointContainer container) {

        }
    };

    public void setReachCallBack(AdjointContainer.OnReachMiddleCallBack aReachCallBack) {
        mReachCallBack = aReachCallBack;
    }

    public float getPrivotX() {
        return privotX;
    }

    public SimpleStyle privotX(float aPrivotX) {
        if(aPrivotX<0){
            aPrivotX=0;
        }
        privotX = aPrivotX;
        return this;
    }

    public float getPrivotY() {
        return privotY;
    }

    public SimpleStyle privotY(float aPrivotY) {
        privotY = aPrivotY;
        if(aPrivotY<0){
            aPrivotY=0;
        }
        privotX = aPrivotY;
        return this;
    }

    public float getMinAlpha() {
        return minAlpha;
    }

    public SimpleStyle minAlpha(float aMinAlpha) {
        if (aMinAlpha < 0) {
            return this;
        }
        minAlpha = aMinAlpha;
        return this;
    }

    public float getMinScale() {
        return minScale;
    }

    public SimpleStyle minScale(float aMinScale) {
        if (aMinScale < 0) {
            return this;
        }
        minScale = aMinScale;
        return this;
    }

    public float getLinearPos() {
        return linearPos;
    }

    public SimpleStyle linearPos(float aLinearPos) {
        if (aLinearPos < 0 || aLinearPos > 1) {
            return this;
        }
        linearPos = aLinearPos;
        return this;
    }

    public boolean isLinearable() {
        return linearable;
    }

    public SimpleStyle linearable(boolean aLinearable) {
        linearable = aLinearable;
        return this;
    }

    public float getFactor() {
        return factor;
    }

    public SimpleStyle factor(float aFactor) {
        if (aFactor < 0 || aFactor > 2) {
            return this;
        }
        factor = aFactor;
        return this;
    }

    @Override
    public abstract void onAttachedToImageView(AdjointContainer view);

    @Override
    public abstract void onDetachedFromImageView(AdjointContainer view);

    @Override
    public abstract void transform(AdjointContainer aContainer, Canvas canvas, int[] itemLocation, Rect listRect);
}
