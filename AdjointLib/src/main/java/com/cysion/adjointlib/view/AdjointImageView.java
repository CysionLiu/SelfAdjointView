package com.cysion.adjointlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cysionLiu on 25/11/2016.
 */

public class AdjointImageView extends ImageView {

    public AdjointImageView(Context context) {
        this(context, null);
    }

    public AdjointImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdjointImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getDrawable() != null) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
