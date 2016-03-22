package com.mywjch.tumbro.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.mywjch.tumbro.R;
import com.mywjch.tumbro.app.App;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by mywjch on 15/10/2.
 */
public class saveImageAsycTask extends AsyncTask<Bitmap, Void, String> {

    @Override
    protected String doInBackground(Bitmap... params) {
        String result = App.getInstance().getResources().getString(R.string.save_image_fail);
        try {
            String sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            File file = new File(sdcard + "/myimg");
            if (!file.exists()) {
                file.mkdir();
            }

            File imageFile = new File(file.getAbsolutePath(),
                    new Date().getTime() + ".jpg");
            FileOutputStream out = new FileOutputStream(imageFile);
            Bitmap bitmap = params[0];
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            result = App.getInstance().getResources().getString(R.string.save_image_success, file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(App.getAppContext(), s, Toast.LENGTH_SHORT).show();
//            viewHolder.imageView.setDrawingCacheEnabled(false);
    }
}
