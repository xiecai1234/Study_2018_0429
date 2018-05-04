package com.example.xiecaibao.study;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/25.
 */

public class ActivityMgr {
    Context context;
    private static ActivityMgr sInstance = new ActivityMgr();
    List<Activity> activities = new ArrayList<Activity>();
    private ActivityMgr(){
    };

    private ActivityMgr(Context context){
        this.context = context;
    };

    public static ActivityMgr getInstance() {
        return sInstance;
    }

    public static ActivityMgr getInstance2(Context context) {
        if (null == sInstance) {
            synchronized (ActivityMgr.class) {
                if (null == sInstance) {
                    sInstance = new ActivityMgr(context);
                }
            }
        }
        return  sInstance;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void removeActivity(Activity activity){
        activities.remove(activity);
    }

}
