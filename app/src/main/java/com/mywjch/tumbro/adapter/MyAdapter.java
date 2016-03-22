package com.mywjch.tumbro.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mywjch.tumbro.R;
import com.mywjch.tumbro.model.Pic;

import java.util.List;

/**
 * Created by mywjch on 15/9/30.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    private List<Pic> data;
    private Context mContext;

    public MyAdapter(List data, Context context) {
        this.data = data;
        mContext = context;
    }

    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycler, null);
        return new ViewHolder(v);
    }

    //将数据绑定到子View，会自动复用View
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText("i");
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,400+(int) (Math.random() * 50));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 450);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 30);
        viewHolder.imageView.setLayoutParams(lp);
        viewHolder.textView.setLayoutParams(lp2);

        if (data.get(i) != null) {
            String url = data.get(i).getUrl();
            if (TextUtils.isEmpty(url)) {
//                viewHolder.imageView.setImageResource(R.mipmap.mm);
//                viewHolder.textView.setText(data.get(i).getFilename());
            } else {
                Uri uri = Uri.parse(url);
                Log.e(TAG, "onBindViewHolder******* " + url);
                Glide.with(mContext).load(url).centerCrop()
                        .placeholder(R.drawable.loading)
                        .crossFade()
                        .into(viewHolder.imageView);
//                viewHolder.imageView.setImageURI(uri);
                viewHolder.textView.setText(data.get(i).getFilename());
            }
        }
    }


    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return data.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        //在布局中找到所含有的UI组件
        public ViewHolder(View view) {
            super(view);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView1);
        }


    }


}
