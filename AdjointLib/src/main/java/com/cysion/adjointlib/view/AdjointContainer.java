package com.cysion.adjointlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.Locator;
import com.cysion.adjointlib.SimpleStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CysionLiu on 2017/8/9.
 */

public class AdjointContainer extends RelativeLayout implements ViewTreeObserver.OnScrollChangedListener {
    private boolean enableScrollParallax = true;
    private int[] viewLocation = new int[2];
    private List<AdjointStyle> mAdjointStyles = new ArrayList<>();
    private Rect parentLocation = new Rect();//parent list rect
    private Locator mLocator;
    private OnReachMiddleCallBack mReachCallBack;
    private int mItemPosition = 0;

    public int getItemPosition() {
        return mItemPosition;
    }

    public void setItemPosition(int aItemPosition) {
        mItemPosition = aItemPosition;
    }

    public void setReachCallBack(OnReachMiddleCallBack aReachCallBack) {
        mReachCallBack = aReachCallBack;
    }
    public void setReachCallBack(OnReachMiddleCallBack aReachCallBack,int aItemPosition) {
        mReachCallBack = aReachCallBack;
        mItemPosition = aItemPosition;
    }

    public interface OnReachMiddleCallBack {
        void reachMiddle(AdjointContainer container);
    }

    public AdjointContainer(Context context) {
        super(context);
        init();
    }

    public AdjointContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdjointContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (getBackground() == null) {
            setBackgroundColor(0x0000);
        }
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

    public void addStyle(AdjointStyle aAdjointStyle) {
        mAdjointStyles.add(aAdjointStyle);
    }

    public void removeStyle(AdjointStyle aAdjointStyle) {
        mAdjointStyles.remove(aAdjointStyle);
    }

    public void clearStyles() {
        mAdjointStyles.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mLocator != null) {
            parentLocation = mLocator.getLocation();
        }
        if (!enableScrollParallax || parentLocation == null || parentLocation.bottom == 0) {
            super.onDraw(canvas);
            return;
        }
        getLocationInWindow(viewLocation);
        boolean addCallback = false;
        for (int i = 0; i < mAdjointStyles.size(); i++) {
            if (!addCallback && mAdjointStyles.get(i) instanceof SimpleStyle&&mReachCallBack!=null) {
                addCallback = true;
                ((SimpleStyle) mAdjointStyles.get(i)).setReachCallBack(mReachCallBack);
            }
            mAdjointStyles.get(i).transform(this, canvas, viewLocation, parentLocation);
        }
        super.onDraw(canvas);
    }

    public void setLocator(Locator aLocator) {
        mLocator = aLocator;
    }

    @Override
    public void onScrollChanged() {
        if (enableScrollParallax) {
            invalidate();
            requestLayout();
        }
    }
}