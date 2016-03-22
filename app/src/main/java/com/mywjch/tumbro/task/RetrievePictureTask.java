package com.mywjch.tumbro.task;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mywjch.tumbro.model.Picture;
import com.mywjch.tumbro.utils.HttpURLUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mywjch on 15/8/21.
 */
public class RetrievePictureTask extends AsyncTask<String, Void, Picture> {
    private static final String TAG = "RetrievePictureTask";
    private Picture p;

    protected Picture doInBackground(String... urls) {
        try {
            new HttpURLUtil().getContent(urls[0], new HttpURLUtil.MyCallback() {
                @Override
                public void OnSuccess(InputStream inputStream) {
                    String result = "";
                    String inputstring = "";
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        while ((inputstring = bufferedReader.readLine()) != null) {
                            result = result + inputstring;
                        }
                        Log.w("TAG", result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p = JSON.parseObject(result, Picture.class);
                }

                @Override
                public void onError(String msg) {
                    Log.d("TAG", msg);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "run========= " + p.getTotalNum());
        return p;
    }
}
