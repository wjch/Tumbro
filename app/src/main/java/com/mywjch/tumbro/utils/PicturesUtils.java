package com.mywjch.tumbro.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.mywjch.tumbro.model.Pic;
import com.mywjch.tumbro.model.Picture;
import com.mywjch.tumbro.task.RetrievePictureTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by mywjch on 15/8/14.
 */
public class PicturesUtils {


    @SuppressWarnings("deprecation")
    public static BitmapDrawable getScaledDrawble(Activity a, String path) {

        Display display = a.getWindowManager().getDefaultDisplay();
        float destWidth = display.getWidth();
        float destHeight = display.getHeight();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;

        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > destHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        Bitmap bitmap = BitmapFactory.decodeFile(path, options);


        return new BitmapDrawable(a.getResources(), bitmap);
    }

    public static void cleanImageView(ImageView imageView) {
        if (!(imageView.getDrawable() instanceof BitmapDrawable))
            return;

        BitmapDrawable b = (BitmapDrawable) imageView.getDrawable();
//        b.getBitmap().recycle();
        imageView.setImageDrawable(null);
    }

    public ArrayList<Pic> getPicture(String tag1, String tag2, String ftags) {
        ArrayList<Pic> data = new ArrayList<Pic>();

        Picture p = null;

        try {
            tag1 = URLEncoder.encode(tag1, "UTF-8");
            tag2 = URLEncoder.encode(tag2, "UTF-8");
            ftags = URLEncoder.encode(ftags, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=" + tag1 +
                "&tag2=" + tag2 + "&ftags=" + ftags + "&ie=utf8";
        try {
            p = new RetrievePictureTask().execute(url).get();
//            Log.e("mywjch", "$$$$$$$$$" + p.getTotalNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < p.getData().size(); i++) {
            String imgurl = p.getData().get(i).getImage_url();
            Pic pic = new Pic();
            if (!TextUtils.isEmpty(imgurl)) {

                pic.setUrl(imgurl);
                pic.setFilename(p.getData().get(i).getDesc());
                Log.e("mywjch", "$$$$$$$$$" + imgurl);

            } else {
                pic.setUrl("");
                pic.setFilename("暂无图片");
            }
            data.add(pic);
        }
//        for (int i = 0; i < data.size() - 1; i++) {
//            Log.e("mywjch", data.get(i).toString());
//        }
        return data;
    }

}
