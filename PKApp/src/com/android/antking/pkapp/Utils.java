package com.android.antking.pkapp;

import android.content.Context;

import java.text.SimpleDateFormat;

public class Utils {
    private final static SimpleDateFormat dateFormater3 = new SimpleDateFormat("HH:mm:ss");
    
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    public static String getDate() {
        return dateFormater3.format(System.currentTimeMillis());
    }

    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defValue;
        }
    }
}
