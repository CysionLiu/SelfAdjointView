package com.cysion.adjointlib.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

/**
 * Created by cysion.liu on 2016/6/20.
 */
public class ScreenUtil {


    public static float getDensity(Context aContext) {

        float density = aContext.getResources().getDisplayMetrics().density;
        return density;
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = getDensity(context);
        return (int) (dpValue * scale + 0.5f);
    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = getDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }


    public static int getScreenWidth(Context aContext) {
        WindowManager manager = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            manager.getDefaultDisplay().getRealMetrics(out);
        } else {
            manager.getDefaultDisplay().getMetrics(out);
        }
        return out.widthPixels;
    }


    public static int getScreenHeight(Context aContext) {
        WindowManager manager = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            manager.getDefaultDisplay().getRealMetrics(out);
        } else {
            manager.getDefaultDisplay().getMetrics(out);
        }
        return out.heightPixels;
    }


    public static int getHeightWithoutVisualBotton(Context aContext) {
        WindowManager manager = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(out);
        return out.heightPixels;
    }


    public static int getStatusBarHeight(Activity activity) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int dpHeight = Integer.parseInt(field.get(object).toString());
            return activity.getResources().getDimensionPixelSize(dpHeight);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }


    public static void hideSystemKeyBoard(Context mcontext, View v) {
        InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}
