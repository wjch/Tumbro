package com.mywjch.tumbro.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLUtil {

    private static final String TAG = "HttpUtil";

    public void getContent(String url, MyCallback callback) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        try {
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();
            Log.e(TAG, "====" + httpURLConnection.getResponseCode());
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                callback.OnSuccess(inputStream);
            } else {
                String msg = "" + httpURLConnection.getResponseCode() + httpURLConnection.getResponseMessage();
                callback.onError(msg);
            }
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public interface MyCallback {
        void OnSuccess(InputStream inputStream);

        void onError(String msg);
    }
}
