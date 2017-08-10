package com.cysion.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

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
        mContainer1.addStyle(new VerticalAlphaStyle());
        mContainer2.addStyle(new VerticalAlphaStyle());
        mContainer3.addStyle(new VerticalAlphaStyle());
        mContainer4.addStyle(new VerticalAlphaStyle());
        mContainer5.addStyle(new VerticalAlphaStyle());
        mContainer6.addStyle(new VerticalAlphaStyle());
        mContainer1.addStyle(new VerticalScaleStyle());
        mContainer2.addStyle(new VerticalScaleStyle());
        mContainer3.addStyle(new VerticalScaleStyle());
        mContainer4.addStyle(new VerticalScaleStyle());
        mContainer5.addStyle(new VerticalScaleStyle());
        mContainer6.addStyle(new VerticalScaleStyle());
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
