package com.cysion.adjointlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cysion.adjointlib.R;

/**
 * Created by cysionLiu on 25/11/2016.
 */

public class AdjointImageView extends ImageView {
    private boolean isVertical = true;

    public AdjointImageView(Context context) {
        this(context, null);
    }

    public AdjointImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdjointImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AdjointContainer);
        isVertical = typedArray.getBoolean(R.styleable.AdjointContainer_isVertical, true);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getDrawable() == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        if (isVertical) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
            setMeasuredDimension(width, height);
        } else {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            int width = height * getDrawable().getIntrinsicWidth() / getDrawable().getIntrinsicHeight();
            setMeasuredDimension(width, height);
        }
    }
}
