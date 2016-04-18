package com.fly.footprint.Finds;

/**
 * Created by Administrator on 2016/4/17 0017.
 */
public class Find {
    private int img;
    private String title;

    public Find(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
