package com.cysion.adjointlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cysion.adjointlib.style.VerticalMoveStyle;

/**
 * Created by CysionLiu on 2017/8/9.
 */

public class AdjointContainer extends RelativeLayout implements ViewTreeObserver.OnScrollChangedListener {
    private boolean enableScrollParallax = true;
    private int[] viewLocation = new int[2];
    private Drawable mDrawable;
    private ImageView mImageView;
    private AdjointStyle mAdjointStyle = new VerticalMoveStyle();

    public void setAdjointStyle(AdjointStyle aAdjointStyle) {
        mAdjointStyle = aAdjointStyle;
    }

    private Rect parentLocation = new Rect();
    private Locator mLocator;

    public void setLocator(Locator aLocator) {
        mLocator = aLocator;
    }

    public void setImageView(ImageView aImageView) {
        mImageView = aImageView;
    }

    public AdjointContainer(Context context) {
        super(context);
        init();
    }

    public AdjointContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdjointContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
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
        if (mLocator != null) {
            parentLocation = mLocator.getLocation();
        }
//        mDrawable = mImageView.getDrawable();
        if (!enableScrollParallax || parentLocation.bottom == 0) {
            super.onDraw(canvas);
            return;
        }
        getLocationInWindow(viewLocation);
        if (mAdjointStyle != null) {
            mAdjointStyle.transform(this, canvas, viewLocation, parentLocation);
        }

//        int y = viewLocation[1];
//        int ptop = parentLocation.top;
//        int pbottom = parentLocation.bottom;
//        // image's width and height
//        int iWidth = mDrawable.getIntrinsicWidth();
//        int iHeight = mDrawable.getIntrinsicHeight();
//        if (iWidth <= 0 || iHeight <= 0) {
//            return;
//        }
//        // view's width and height
//        int vWidth = getWidth() - getPaddingLeft() - getPaddingRight();
//        int vHeight = getHeight() - getPaddingTop() - getPaddingBottom();
//
//        // device's height
//        int dHeight = getResources().getDisplayMetrics().heightPixels;
//        dHeight = dHeight < pbottom ? dHeight : pbottom;
//        if (iWidth * vHeight < iHeight * vWidth || iHeight > vHeight) {
//            // avoid over scroll
//            if (y < ptop - vHeight) {
//                y = ptop - vHeight;
//            } else if (y > dHeight) {
//                y = dHeight;
//            }
//
//            float imgScale = (float) vWidth / (float) iWidth;
//            float max_dy = Math.abs((iHeight * imgScale - vHeight));
//            y = y - ptop;
//            int farY = pbottom - ptop - vHeight;
//            float translateY = -(max_dy * y / farY);
//            canvas.translate(0, translateY);
//        }
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