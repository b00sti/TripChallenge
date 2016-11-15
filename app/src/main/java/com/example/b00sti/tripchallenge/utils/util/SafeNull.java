package com.example.b00sti.tripchallenge.utils.util;

import android.support.annotation.Nullable;

import java.util.Collection;

import rx.Subscription;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-15
 */

public class SafeNull {
    //prevent creating object
    private SafeNull() {
    }

    public static Throwable NULL_POINTER_EXCEPTION(String TAG) {

        return new Throwable(TAG + ", Null pointer exception!");
    }

    public static <T> int safeCount(Collection<T> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static <T> boolean isNonEmpty(Collection<T> collection) {
        return safeCount(collection) > 0;
    }

    public static void safeUnsubscribe(@Nullable rx.Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static void forceUnsubscribe(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static boolean stringNonEmpty(String s) {
        return s != null && !(s.length() == 0);
    }
}
