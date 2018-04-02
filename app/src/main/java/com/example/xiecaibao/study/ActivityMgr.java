package com.example.xiecaibao.study;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/25.
 */

public class ActivityMgr {
    private static ActivityMgr sInstance = new ActivityMgr();
    List<Activity> activities = new ArrayList<Activity>();
    private ActivityMgr(){
    };

    public static ActivityMgr getInstance() {
        return sInstance;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void removeActivity(Activity activity){
        activities.remove(activity);
    }

}
