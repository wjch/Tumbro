package com.mywjch.tumbro.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mywjch on 16/3/14.
 */
public class MessageUtil {

    public static void ShowShortMessage(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static void ShowLongMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG);
    }
}
