package com.cysion.adjointlib.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cysion.liu on 2016/6/20.
 * 调试工具类
 * 1--纯吐司测试
 * 2--纯log测试
 * 3--自定义位置的普通吐司
 * 4--自定义视图吐司
 */
public class ALog {

    private boolean debug = true;

    private static volatile ALog instance = new ALog();
    private Context mContext;

    private ALog() {
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean aDebug) {
        debug = aDebug;
    }

    public static synchronized ALog single() {
        return instance;
    }

    public void init(Context aCon) {
        mContext = aCon;
    }

    /**
     * @param text
     */
    public void toast(String text) {
        if (instance.mContext == null) {
            instance.le("error", "should invoke method init(..)");
            return;
        }
        if (debug) {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param text
     */
    public void ld(String text) {
        if (debug) {
            Log.d("adjoint--", text);
        }
    }

    /**
     * @param tag
     * @param text
     */
    public void le(String tag, String text) {
        if (debug) {
            Log.e(tag, text);
        }
    }
}
