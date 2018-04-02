package com.example.xiecaibao.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.xiecaibao.study.ActivityMgr;
import com.example.xiecaibao.study.R;

/**
 * Created by Administrator on 2018/3/25.
 */

public class LeakActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        ActivityMgr.getInstance().addActivity(this);
    }
}
