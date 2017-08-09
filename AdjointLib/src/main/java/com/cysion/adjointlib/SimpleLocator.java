package com.cysion.adjointlib;

import android.graphics.Rect;

/**
 * Created by Administrator on 2017/8/9.
 */

public class SimpleLocator implements Locator {
    private Rect parentFrame;

    @Override
    public Rect getLocation() {
        if (parentFrame == null) {
            try {
                throw new Exception("should invoke setParentFrame method");
            } catch (Exception aE) {
                aE.printStackTrace();
            }
        }
        return parentFrame;
    }

    private static volatile SimpleLocator instance = new SimpleLocator();

    private SimpleLocator() {
    }

    public static synchronized SimpleLocator single() {
        return instance;
    }

    public void setParentFrame(Rect aParentFrame) {
        parentFrame = aParentFrame;
    }
}
