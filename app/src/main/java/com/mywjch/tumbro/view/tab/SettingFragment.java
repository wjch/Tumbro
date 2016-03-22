package com.mywjch.tumbro.view.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.mywjch.tumbro.model.Pic;
import com.mywjch.tumbro.model.Picture;
import com.mywjch.tumbro.utils.PicturesUtils;

import java.util.ArrayList;

public class SettingFragment extends BaseFragment {
    private static final String TAG = "SettingFragment";
    private String url;
    private RecyclerView recyclerView;
    private ArrayList<Pic> data = null;
    private Handler myHandler;
    private Picture p = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public ArrayList<Pic> getData() {
        PicturesUtils picturesUtils = new PicturesUtils();
        data = picturesUtils.getPicture("动漫", "全部", "二次元");
        return data;
    }
}
