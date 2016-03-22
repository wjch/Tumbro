package com.mywjch.tumbro.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mywjch.tumbro.R;
import com.mywjch.tumbro.model.Pic;

import java.util.List;

/**
 * Created by mywjch on 15/9/30.
 */
public class ListTwoAdapter extends BaseAdapter {
    private static final String TAG = "MyAdapter";
    private List<Pic> data;
    private Context mContext;

    public ListTwoAdapter(List data, Context context) {
        this.data = data;
        mContext = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View recycleView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (recycleView == null) {
            mViewHolder = new ViewHolder();
            recycleView = LayoutInflater.from(mContext).inflate(R.layout.recycler1, null);
            mViewHolder.textView = (TextView) recycleView.findViewById(R.id.listtextView);
            mViewHolder.imageView = (ImageView) recycleView.findViewById(R.id.listimageView);
            recycleView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) recycleView.getTag();
        }

        if (data.get(position) != null) {
            String url = data.get(position).getUrl();
            if (TextUtils.isEmpty(url)) {
//                mViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
                mViewHolder.textView.setText("没有图啦");
            } else {
                Log.e(TAG, "onBindViewHolder******* " + url);
                Glide.with(mContext).load(url).fitCenter()
                        .placeholder(R.drawable.loading).crossFade()
                        .override(400, 420)
                        .into(mViewHolder.imageView);
                mViewHolder.textView.setText(data.get(position).getFilename());
            }
        }
        return recycleView;

    }

    //自定义的ViewHolder,减少findViewById调用次数
    static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}
