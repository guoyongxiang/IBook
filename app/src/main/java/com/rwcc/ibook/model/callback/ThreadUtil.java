package com.rwcc.ibook.model.callback;

import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static Thread mThread = Looper.getMainLooper().getThread();

    public static void postTaskSafely(final Runnable runnable) {
        if (getmThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            getmHandler().post(runnable);
        }
    }

    public static Thread getmThread() {
        return mThread;
    }

    public static Handler getmHandler() {
        return mHandler;
    }
}
