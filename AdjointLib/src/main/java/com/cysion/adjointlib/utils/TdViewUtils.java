package com.cysion.adjointlib.utils;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cysion.liu on 2016/6/21.
 * view工具类，目前功能包括：
 * 1--获得view的高度、宽度;
 * 2--设置view的高度、宽度;
 * 3--遍历获得view中所有的子view
 */
public class TdViewUtils {


    /**
     * 设置view的高度
     * @param view
     * @param height
     */
    public static void setViewHeight(View view, int height) {
        if (view == null) {
            return;
        }
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (params==null){
            return;
        }
        params.height = height;
        view.setLayoutParams(params);
    }

    /**
     * 设置view的宽度
     * @param view
     * @param width
     */
    public static void setViewWidth(View view, int width) {
        if (view == null) {
            return;
        }
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (params==null){
            return;
        }
        params.width = width;
        view.setLayoutParams(params);
    }

    /**
     * 获得view的宽度
     * @param view
     */
    public static int getViewWidth(View view) {
        if (view == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (params==null){
            return 0;
        }
        return params.width;
    }


    /**
     * 获得view的高度
     * @param view
     */
    public static int getViewHeight(View view) {
        if (view == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (params==null){
            return 0;
        }
        return params.height;
    }

    /**
     * 遍历获得viewGroup中所有的子view
     * @param aDecorView viewgroup
     */
    private List<View> getChildren(ViewGroup aDecorView) {
        List<View> childViews = new ArrayList<>();
        int childCount = aDecorView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            childViews.add(aDecorView.getChildAt(i));
        }
        return childViews;
    }

}


