package com.cysion.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.ScrollView;

import com.cysion.adjointlib.AdjointStyle;
import com.cysion.adjointlib.Locator;
import com.cysion.adjointlib.style.VerticalAlphaStyle;
import com.cysion.adjointlib.style.VerticalScaleStyle;
import com.cysion.adjointlib.view.AdjointContainer;

public class SecondActivity extends AppCompatActivity implements Locator {

    private ScrollView mScrollView;
    private AdjointContainer mContainer1;
    private AdjointContainer mContainer2;
    private AdjointContainer mContainer3;
    private AdjointContainer mContainer4;
    private AdjointContainer mContainer5;
    private AdjointContainer mContainer6;
    private Rect mR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mScrollView = (ScrollView) findViewById(R.id.scroller);
        mContainer1 = (AdjointContainer) findViewById(R.id.adcontainer1);
        mContainer2 = (AdjointContainer) findViewById(R.id.adcontainer2);
        mContainer3 = (AdjointContainer) findViewById(R.id.adcontainer3);
        mContainer4 = (AdjointContainer) findViewById(R.id.adcontainer4);
        mContainer5 = (AdjointContainer) findViewById(R.id.adcontainer5);
        mContainer6 = (AdjointContainer) findViewById(R.id.adcontainer6);
        final SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.sw);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mContainer1.clearStyles();
                mContainer2.clearStyles();
                mContainer3.clearStyles();
                mContainer4.clearStyles();
                mContainer5.clearStyles();
                mScrollView.requestLayout();
                mScrollView.invalidate();
                if (!isChecked) {
                    addAnimation1();
                } else {
                    addAnimation2();
                }
            }
        });
        addAnimation1();
        mR = new Rect();
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.getGlobalVisibleRect(mR);
                mContainer1.setLocator(SecondActivity.this);
                mContainer2.setLocator(SecondActivity.this);
                mContainer3.setLocator(SecondActivity.this);
                mContainer4.setLocator(SecondActivity.this);
                mContainer5.setLocator(SecondActivity.this);
                mContainer6.setLocator(SecondActivity.this);
            }
        });
    }

    private void addAnimation2() {
        AdjointStyle style = new VerticalScaleStyle().minScale(0.85f);
        mContainer1.addStyle(style);
        mContainer2.addStyle(style);
        mContainer3.addStyle(style);
        mContainer4.addStyle(style);
        mContainer5.addStyle(style);
        AdjointStyle style2 = new VerticalAlphaStyle().minAlpha(0.3f);
        mContainer1.addStyle(style2);
        mContainer2.addStyle(style2);
        mContainer3.addStyle(style2);
        mContainer4.addStyle(style2);
        mContainer5.addStyle(style2);
    }

    private void addAnimation1() {
        AdjointStyle style = new VerticalAlphaStyle().linearable(true).minAlpha(0.3f);
        mContainer1.addStyle(style);
        mContainer2.addStyle(style);
        mContainer3.addStyle(style);
        mContainer4.addStyle(style);
        mContainer5.addStyle(style);
        AdjointStyle ver = new VerticalScaleStyle().minScale(0.85f).linearable(true).linearPos(0.15f);
        mContainer1.addStyle(ver);
        mContainer2.addStyle(ver);
        mContainer3.addStyle(ver);
        mContainer4.addStyle(ver);
        mContainer5.addStyle(ver);
    }

    @Override
    public Rect getLocation() {
        return mR;
    }
}
