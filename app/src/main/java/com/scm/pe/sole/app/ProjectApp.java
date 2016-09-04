package com.scm.pe.sole.app;

import android.app.Application;

import com.scm.pe.sole.utils.NetVolley;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class ProjectApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetVolley.init(this);
    }
}
