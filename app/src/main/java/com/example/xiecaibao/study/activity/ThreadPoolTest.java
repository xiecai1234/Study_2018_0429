package com.example.xiecaibao.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiecaibao.study.utils.LogX;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ThreadPoolTest extends AppCompatActivity {
    private String TAG = "xcb";
    private static Object lock = new Object();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
    }

    private void test() {
//        waitAndNotifyAll();
    }

    private void waitAndNotifyAll() {
        LogX.d(TAG, "main thread run");
        new Thread1().start();
        long startTime = System.currentTimeMillis();
        try {
            synchronized (lock) {
                LogX.d(TAG, "main thread wait");
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        LogX.d(TAG, "main thread resume:" + (endTime - startTime));
    }

    class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                synchronized (lock){
                    LogX.d(TAG, "Thread1 run");
                    Thread.sleep(3 * 1000);
                    LogX.d(TAG, "Thread1 end");
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
