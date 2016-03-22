package com.mywjch.tumbro.view.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mywjch.tumbro.R;
import com.mywjch.tumbro.adapter.ListTwoAdapter;
import com.mywjch.tumbro.model.Pic;

import java.util.ArrayList;

public abstract class BaseListFragment extends Fragment {

    private ListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab02, container, false);
        listview = (ListView) v.findViewById(R.id.listview);
        listview.setAdapter(new ListTwoAdapter(getData(), getActivity()));
        return v;
    }

    public abstract ArrayList<Pic> getData();

    @Override
    public void onDestroy() {
//		PostManager.getSingleBus().unregister(this);
        super.onDestroy();

    }
}
