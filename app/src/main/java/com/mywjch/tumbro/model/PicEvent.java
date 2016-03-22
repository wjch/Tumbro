package com.mywjch.tumbro.model;

import java.util.ArrayList;

/**
 * Created by mywjch on 15/8/18.
 */
public class PicEvent {
    private ArrayList data;
    private String url;
    private String filename;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
}
