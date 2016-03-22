package com.mywjch.tumbro.view.tab;

import android.os.Bundle;

import com.mywjch.tumbro.model.Pic;
import com.mywjch.tumbro.utils.PicturesUtils;

import java.util.ArrayList;

public class PicFragment extends BaseFragment {
    private static final String TAG = "PicFragment";
    private ArrayList<Pic> data = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<Pic> getData() {
        PicturesUtils picturesUtils = new PicturesUtils();
        data = picturesUtils.getPicture("美女", "全部", "嫩萝莉");
        return data;
    }
}
