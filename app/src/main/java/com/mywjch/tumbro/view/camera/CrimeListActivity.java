package com.mywjch.tumbro.view.camera;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.mywjch.tumbro.R;
import com.mywjch.tumbro.app.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {

    public static final String TAG = "CrimeListActivity";

    @Override
    protected Fragment getFragment() {
        return new CrimeListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActionBar actionBar = getActionBar();
        actionBar.show();// 显示ActionBar
        Log.e(TAG, "onCreate " + actionBar.isShowing());
        setContentView(R.layout.activity_fragment);
    }
}
