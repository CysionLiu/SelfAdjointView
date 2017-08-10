package com.cysion.adjointlib;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class AdjConfig {
    private float min = 0.0f;
    private float linearPos = 0.00f;
    private boolean linearable = false;
    private float multi = 0f;

    public float getMin() {
        return min;
    }

    public AdjConfig setMin(float aMin) {
        min = aMin;
        return this;
    }

    public float getLinearPos() {
        return linearPos;
    }

    public AdjConfig setLinearPos(float aLinearPos) {
        linearPos = aLinearPos;
        return this;
    }

    public boolean isLinearable() {
        return linearable;
    }

    public AdjConfig setLinearable(boolean aLinearable) {
        linearable = aLinearable;
        return this;
    }

    public float getMulti() {
        return multi;
    }

    public AdjConfig setMulti(float aMulti) {
        multi = aMulti;
        return this;
    }
}
