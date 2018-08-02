package com.example.administrator.myapplication.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.myapplication.BuildConfig;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * 功能：
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/6
 */
public class LogUtils {
    public static final int MAX_LENGTH = 3900;

    public static void v(String TAG, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.v(TAG, m);
                }

            } else {
                Log.v(TAG, msg);
            }
        }
    }

    public static void d(String TAG, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.d(TAG, m);
                }

            } else {
                Log.d(TAG, msg);
            }
        }
    }


    public static void i(String TAG, String msg) {

        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.i(TAG, m);
                }

            } else {
                Log.i(TAG, msg);
            }
        }
    }

    public static void e(String TAG, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.e(TAG, m);
                }

            } else {
                Log.e(TAG, msg);
            }
        }
    }


    public static void e(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            String tag = getTag();
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.e(tag, m);
                }
            } else {
                Log.e(tag, msg);
            }
        }
    }


    public static void d(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            String tag = getTag();
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.d(tag, m);
                }
            } else {
                Log.d(tag, msg);
            }
        }
    }

    public static void v(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            String tag = getTag();
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.v(tag, m);
                }
            } else {
                Log.v(tag, msg);
            }
        }
    }


    public static void i(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (BuildConfig.DEBUG) {
            String tag = getTag();
            if (msg.length() > MAX_LENGTH) {

                for (String m : splitList(msg)) {
                    Log.i(tag, m);
                }
            } else {
                Log.i(tag, msg);
            }
        }
    }

    private static List<String> splitList(String msg) {
        List<String> stringList = new ArrayList<>();
        int index = 0;
        int maxLength = MAX_LENGTH;
        int countOfSub = msg.length() / maxLength;
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                stringList.add(sub);
                index += maxLength;
            }
            stringList.add(msg.substring(index, msg.length()));
        } else {
            stringList.add(msg);
        }
        return stringList;

    }

    private static String getTag() {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
        boolean shouldTrace = false;
        StackTraceElement current = null;
        for (StackTraceElement stackTraceElement1 : stackTraceElement) {
            boolean isLog = stackTraceElement1.getClassName().equals(LogUtils.class.getName());
            if (shouldTrace && !isLog) {
                current = stackTraceElement1;
                break;
            }
            shouldTrace = isLog;

        }
        String className = current.getClassName().substring(current.getClassName().lastIndexOf(".") + 1);
        stringBuilder.append(className).append("  line:").append(current.getLineNumber());
        return stringBuilder.toString();

    }
}
