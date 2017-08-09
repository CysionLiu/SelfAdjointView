package com.cysion.adjointlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by CysionLiu on 2017/8/9.
 */

public class AdjointContainer extends RelativeLayout implements ViewTreeObserver.OnScrollChangedListener {
    private boolean enableScrollParallax = true;
    private int[] viewLocation = new int[2];
    private Drawable mDrawable;
    private ImageView mImageView;

    public void setImageView(ImageView aImageView) {
        mImageView = aImageView;
    }

    public void setDrawable(Drawable aDrawable) {
        mDrawable = aDrawable;
    }

    public AdjointContainer(Context context) {
        super(context);
        setBackgroundColor(0x0000);
    }

    public AdjointContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(0x0000);
    }

    public AdjointContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(0x0000);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        getViewTreeObserver().removeOnScrollChangedListener(this);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDrawable = mImageView.getDrawable();
        if (!enableScrollParallax || mDrawable == null) {
            super.onDraw(canvas);
            return;
        }

//        if (parallaxStyles != null){
//            parallaxStyles.transform(this, canvas, viewLocation[0], viewLocation[1]);
//        }
        getLocationInWindow(viewLocation);
        int y = viewLocation[1];
        // image's width and height
        int iWidth = mDrawable.getIntrinsicWidth();
        int iHeight = mDrawable.getIntrinsicHeight();
        if (iWidth <= 0 || iHeight <= 0) {
            return;
        }
        // view's width and height
        int vWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int vHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        // device's height
        int dHeight = getResources().getDisplayMetrics().heightPixels;
        if (iWidth * vHeight < iHeight * vWidth) {
            // avoid over scroll
            if (y < -vHeight) {
                y = -vHeight;
            } else if (y > dHeight) {
                y = dHeight;
            }

            float imgScale = (float) vWidth / (float) iWidth;
            float max_dy = Math.abs((iHeight * imgScale - vHeight) * 0.5f);
            float translateY = -(2 * max_dy * y + max_dy * (vHeight - dHeight)) / (vHeight + dHeight);
            canvas.translate(0, translateY * 2.5f);
        }
        super.onDraw(canvas);
    }

    @Override
    public void onScrollChanged() {
        if (enableScrollParallax) {
            invalidate();
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}