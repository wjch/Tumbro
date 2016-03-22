package com.mywjch.tumbro.listener;

import android.view.View;

public interface ItemClickListener {

    /**
     * Item 普通点击
     */

    public void onItemClick(View view, int postion);

    /**
     * Item 长按
     */

    public void onItemLongClick(View view, int postion);

    /**
     * Item 内部View点击
     */

    public void onItemSubViewClick(View view, int postion);
}