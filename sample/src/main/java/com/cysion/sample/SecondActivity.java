package com.cysion.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        AdjointStyle style = new VerticalAlphaStyle();
        mContainer1.addStyle(style);
        mContainer2.addStyle(style);
        mContainer3.addStyle(style);
        mContainer4.addStyle(style);
        mContainer5.addStyle(style);
//        mContainer6.addStyle(new VerticalAlphaStyle().linearable(true));
        AdjointStyle ver  = new VerticalScaleStyle().minScale(0.9f);
        mContainer1.addStyle(ver);
        mContainer2.addStyle(ver);
        mContainer3.addStyle(ver);
        mContainer4.addStyle(ver);
        mContainer5.addStyle(ver);
//        mContainer6.addStyle(new VerticalScaleStyle().linearable(true));
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

    @Override
    public Rect getLocation() {
        return mR;
    }
}
