package com.mywjch.tumbro.view.tab;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mywjch.tumbro.R;
import com.mywjch.tumbro.adapter.ItemClickSupport;
import com.mywjch.tumbro.adapter.MyAdapter;
import com.mywjch.tumbro.model.Pic;
import com.mywjch.tumbro.task.saveImageAsycTask;
import com.mywjch.tumbro.utils.OnVerticalScrollListener;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab01, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager mgr = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mgr);
        recyclerView.setAdapter(new MyAdapter(getData(), getActivity()));
        final ItemClickSupport itemClick = ItemClickSupport.addTo(recyclerView);
        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View child, int position, long id) {
                Toast.makeText(getActivity(), "Item clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        itemClick.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView parent, View child, int position, long id) {
                child.setDrawingCacheEnabled(true);
                Bitmap imageBitmap = child.getDrawingCache();
                if (imageBitmap != null) {
                    new saveImageAsycTask().execute(imageBitmap);
                }
                return true;
            }
        });
        recyclerView.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToBottom() {
                Toast.makeText(getActivity(), "已经到底啦", Toast.LENGTH_LONG);

            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
            }

            @Override
            public void onScrolledToTop() {
                Toast.makeText(getActivity(), "已经到顶部啦", Toast.LENGTH_LONG);
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
            }
        });
        return v;
    }

    public abstract ArrayList<Pic> getData();

    @Override
    public void onDestroy() {
//		PostManager.getSingleBus().unregister(this);
        super.onDestroy();

    }
}
