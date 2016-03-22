package com.mywjch.tumbro.utils;

import com.squareup.otto.Bus;

import java.util.ArrayList;

/**
 * Created by mywjch on 15/8/18.
 */
public class PostManager {

    private static Bus bus;
    private ArrayList list;

    public static Bus getSingleBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }


    public void post(int id) {
        switch (id) {
//            case PicFragment.PIC_CODE:
//                PicEvent picEvent = new PicEvent();
//                list=new ArrayList();
//                Picture p=PicturesUtils.getPic("http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=%E6%98%8E%E6%98%9F&tag2=%E5%85%A8%E9%83%A8&ftags=%E5%A5%B3%E6%98%8E%E6%98%9F##内地&ie=utf8");
//                if(p!=null) {
//                    for (int i = 0; i < p.getData().size(); i++) {
//                        String name = p.getData().get(i).getThumbnail_url();
//                        list.add(name);
//                    }
//                }
//                picEvent.setData(list);
//                bus.post(picEvent);
//                break;
        }
    }
}
