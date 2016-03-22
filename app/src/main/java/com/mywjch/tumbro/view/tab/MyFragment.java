package com.mywjch.tumbro.view.tab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mywjch.tumbro.R;
import com.mywjch.tumbro.adapter.ItemClickSupport;
import com.mywjch.tumbro.adapter.MyAdapter;
import com.mywjch.tumbro.model.Pic;
import com.mywjch.tumbro.model.Picture;
import com.mywjch.tumbro.task.saveImageAsycTask;
import com.mywjch.tumbro.utils.OnVerticalScrollListener;
import com.mywjch.tumbro.utils.PicturesUtils;

import java.util.ArrayList;

public class MyFragment extends Fragment {
    public static final int RES_CODE1 = 0X01;
    private static final String TAG = "MyFragment";
    private String url;
    private ArrayList<Pic> data;
    private Handler myHandler;
    private Picture p = null;
    private Button back;
    private RecyclerView recyclerView;
    private Callback mCallback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab03, container, false);
        back = (Button) v.findViewById(R.id.back);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView03);

        final StaggeredGridLayoutManager mgr = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mgr);
        recyclerView.setAdapter(new MyAdapter(getData(), getActivity()));

        recyclerView.addOnScrollListener(new OnVerticalScrollListener(){
            @Override
            public void onScrolledToBottom() {
                Toast.makeText(getActivity(),"已经到底啦",Toast.LENGTH_LONG);
            }

            @Override
            public void onScrolledToTop() {
                back.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolledDown() {
                back.setVisibility(View.GONE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.back();
            }
        });
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
        return v;
    }

    public ArrayList<Pic> getData() {
        PicturesUtils picturesUtils = new PicturesUtils();
        data = picturesUtils.getPicture("宠物", "全部", "");
        return data;
    }

    @Override
    public void onDestroy() {
//		PostManager.getSingleBus().unregister(this);
        super.onDestroy();

    }

    interface Callback{
        void back();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback= (Callback) activity;
    }
}
