package com.fly.footprint;

import android.app.Application;

/**
 * Created by Fly on 2016/4/18 0018.
 */
public class Init extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //EaseUI.getInstance().init(getApplicationContext(),null);
    }
}
