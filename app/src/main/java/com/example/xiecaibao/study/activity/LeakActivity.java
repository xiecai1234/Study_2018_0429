package com.example.xiecaibao.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xiecaibao.study.ActivityMgr;
import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.utils.LogX;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/3/25.
 */

public class LeakActivity extends AppCompatActivity {
    private static final String TAG = "xcb";
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
//        ActivityMgr.getInstance().addActivity(this);
//        ActivityMgr.getInstance2(this.getApplicationContext());
//        test1();
//        fetchData();
//        fetchData2();
//        startAsyncTask();
        test2();
    }

    public static void test1() {
        //匿名内部类会引用其外围实例LeakAty.this,所以会导致内存泄漏

        //解决方案
        //将非静态匿名内部类修改为静态匿名内部类
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void fetchData() {
        //获取数据
        mHandler.sendEmptyMessage(0);
    }

    private void fetchData2() {
        //获取数据
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessage(0);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    // 刷新数据
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }

        }

        ;
    };

    private static class MyHandler extends Handler {
        private WeakReference<LeakActivity> leakActivityWeakReference;

        public MyHandler(LeakActivity activity) {
            this.leakActivityWeakReference = new WeakReference<LeakActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LeakActivity activity = leakActivityWeakReference == null ? null : leakActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            Toast.makeText(activity, "feach data success!", Toast.LENGTH_SHORT).show();
        }
    }

    void startAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                while (true) ;
            }
        }.execute();
    }

    void test2() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogX.d(TAG, "delayed message run");
            }
        }, 60 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}
