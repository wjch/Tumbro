package com.mywjch.tumbro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivityFragment fragment1 = new MainActivityFragment();
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.www, fragment1).commit();

    }


}
