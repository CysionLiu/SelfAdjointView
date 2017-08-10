package com.cysion.sample.helper;

import android.graphics.Rect;

import com.cysion.adjointlib.Locator;

/**
 * Created by Cysion Liu on 2017/8/10.
 */

public class HoriLocator implements Locator {
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

    private static volatile HoriLocator instance = new HoriLocator();

    private HoriLocator() {
    }

    public static synchronized HoriLocator single() {
        return instance;
    }

    public void setParentFrame(Rect aParentFrame) {
        parentFrame = aParentFrame;
    }
}
