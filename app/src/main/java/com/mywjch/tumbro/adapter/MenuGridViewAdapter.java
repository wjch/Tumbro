package com.mywjch.tumbro.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mywjch.tumbro.R;
import com.mywjch.tumbro.view.tab.MainTabActivity;

import java.util.ArrayList;
import java.util.List;


public class MenuGridViewAdapter extends BaseAdapter {

    List<ActivityModel> list = new ArrayList<ActivityModel>();
    Activity context;

    public MenuGridViewAdapter(Activity context) {
        this.context = context;
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, "缴费"));
        list.add(new ActivityModel(null, R.drawable.mm, "缴费撤销"));
        list.add(new ActivityModel(null, R.drawable.mm, "退货"));
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, ""));
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, ""));
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, ""));
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, ""));
        list.add(new ActivityModel(null, R.drawable.mm, ""));
        list.add(new ActivityModel(null, R.drawable.mm, "签到"));
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, "写入主密钥"));
        list.add(new ActivityModel(MainTabActivity.class, R.drawable.mm, ""));

    }

    public void setList(List<ActivityModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void add(ActivityModel data) {
        if (list == null)
            list = new ArrayList<ActivityModel>();
        list.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public ActivityModel getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.menu_gridview, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ActivityModel data = list.get(position);
        if (data.iconId != 0)
            holder.iv.setImageResource(data.iconId);
        holder.title.setText(data.title);
        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Toast.makeText(context, "" + position, Toast.LENGTH_SHORT)
                // .show();
                if (data.activity != null)
                    context.startActivity(new Intent(context, data.activity));
                //	ToActivity.toActivity(context, data.activity);
            }
        });
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public static class ActivityModel {
        public Class<?> activity;
        public int iconId;
        public String title;

        public ActivityModel(Class<?> activity, int iconId, String title) {
            this.activity = activity;
            this.iconId = iconId;
            this.title = title;
        }
    }

    class ViewHolder {
        ImageView iv;
        TextView title;
    }
}
