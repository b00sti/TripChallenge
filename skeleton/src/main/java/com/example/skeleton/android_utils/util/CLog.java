package com.example.skeleton.android_utils.util;

import android.util.Log;

import java.util.List;

import retrofit.RetrofitError;

import static android.content.ContentValues.TAG;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class CLog {

    public static <T> void logListSize(String tag, String title, List<T> list) {
        if (list == null) {
            Log.e(tag, title + ": list is null!");
        } else {
            Log.i(TAG, title + ": list size = [" + list.size() + "]");
        }
    }

    public static <T> void logList(String tag, String title, List<T> list) {
        if (list == null) {
            Log.e(tag, title + ": list is null!");
        } else {
            int i = 0;
            for (T t : list) {
                Log.i(TAG, "item " + i + " = [" + t.toString() + "]");
                i++;
            }
        }
    }

    public static void logErrors(String TAG, Throwable throwable) {
        if (throwable == null) {
            Log.e(TAG, "logErrors : throwable is null!");
        } else {
            Log.e(TAG, throwable.getMessage());
            if (throwable instanceof RetrofitError) {
                Log.e(TAG, "url = " + ((RetrofitError) throwable).getUrl());
                Log.e(TAG, "kind of error: " + ((RetrofitError) throwable).getKind().name());
                if (((RetrofitError) throwable).getResponse() != null) {
                    Log.e(TAG, "status code: " + ((RetrofitError) throwable).getResponse().getStatus());
                }
            }

            throwable.printStackTrace();
        }
    }


    public static String getFriendlyVisibility(int visibility) {
        switch (visibility) {
            case 0:
                return "VISIBLE";
            case 4:
                return "INVISIBLE";
            case 8:
                return "GONE";
            default:
                return "NONE";
        }
    }

    public static <T> void d(String TAG, String info, T t) {
        String s1 = null;
        if (t != null) s1 = t.toString();
        Log.d(TAG, info + " = [" + s1 + "]");
    }

    public static <T, S> void d2(String TAG, String info, T t, String info2, S s) {
        String s1 = null;
        String s2 = null;
        if (t != null) s1 = t.toString();
        if (s != null) s2 = s.toString();
        Log.d(TAG, info + " = [" + s1 + "], " + info2 + " = [" + s2 + "]");
    }

    public static <T, S, W> void d3(String TAG, String info, T t, String info2, S s, String info3, W w) {
        String s1 = null;
        String s2 = null;
        String s3 = null;
        if (t != null) s1 = t.toString();
        if (s != null) s2 = s.toString();
        if (w != null) s3 = w.toString();
        Log.d(TAG, info + " = [" + s1 + "], " + info2 + " = [" + s2 + "], " + info3 + " = [" + s3 + "]");
    }

    public static <T> void i1(String TAG, String info, T t) {
        String s1 = null;
        if (t != null) s1 = t.toString();
        Log.i(TAG, info + " = [" + s1 + "]");
    }

    public static <T, S> void i2(String TAG, String info, T t, String info2, S s) {
        String s1 = null;
        String s2 = null;
        if (t != null) s1 = t.toString();
        if (s != null) s2 = s.toString();
        Log.i(TAG, info + " = [" + s1 + "], " + info2 + " = [" + s2 + "]");
    }

    public static <T, S, W> void i3(String TAG, String info, T t, String info2, S s, String info3, W w) {
        String s1 = null;
        String s2 = null;
        String s3 = null;
        if (t != null) s1 = t.toString();
        if (s != null) s2 = s.toString();
        if (w != null) s3 = w.toString();
        Log.i(TAG, info + " = [" + s1 + "], " + info2 + " = [" + s2 + "], " + info3 + " = [" + s3 + "]");
    }

}
