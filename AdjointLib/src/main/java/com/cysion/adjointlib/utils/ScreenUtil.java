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

    /**
     * 获得屏幕密度
     *
     * @param aContext 上下文信息
     * @return
     */
    public static float getDensity(Context aContext) {

        float density = aContext.getResources().getDisplayMetrics().density;
        return density;
    }

    /**
     * 根据手机的分辨率从 dp或者sp 的单位 转成为 px(像素)
     *
     * @param context 上下文
     * @param dpValue dp或者sp
     * @return px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = getDensity(context);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp或者sp
     *
     * @param context 上下文
     * @param pxValue px
     * @return dp或者sp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = getDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获得屏幕宽度，考虑到了虚拟按键
     *
     * @param aContext 上下文
     * @return 屏幕宽度
     */
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

    /**
     * 获得屏幕高度，考虑到了虚拟按键
     *
     * @param aContext 上下文
     * @return 屏幕宽度
     */
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

    /**
     * 获得除去虚拟键的屏幕高度
     *
     * @param aContext 上下文
     * @return 屏幕宽度
     */
    public static int getHeightWithoutVisualBotton(Context aContext) {
        WindowManager manager = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics out = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(out);
        return out.heightPixels;
    }

    /**
     * 获取系统状态栏高度
     *
     * @param activity Activity
     * @return 状态栏高度
     */
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

    /**
     * 隐藏键盘
     *
     * @param mcontext
     * @param v
     */
    public static void hideSystemKeyBoard(Context mcontext, View v) {
        InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}
