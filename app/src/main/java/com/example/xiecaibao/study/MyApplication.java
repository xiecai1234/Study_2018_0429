package com.example.xiecaibao.study;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
//import com.squareup.leakcanary.LeakCanary;
//import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2018/3/25.
 */

public class MyApplication extends Application {
//    static RefWatcher mRefWatcher = null;
    private static RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
//        mRefWatcher = LeakCanary.install(this);
        requestQueue = Volley.newRequestQueue(this);
    }

//    public static RefWatcher getRefWatcher() {
//        return mRefWatcher;
//    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
