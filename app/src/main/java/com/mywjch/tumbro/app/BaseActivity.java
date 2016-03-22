package com.mywjch.tumbro.app;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {


    public void ShowShortMessage(String msg) {

        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
    }

    public void ShowLongMessage(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG);
    }


}
