package com.example.xiecaibao.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.fragment.ArticleListFragment;
import com.example.xiecaibao.study.utils.LogX;

public class MyTestFragmentActivity extends AppCompatActivity {
    private static final String TAG = "MyTestFragmentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test);
        super.onCreate(savedInstanceState);
        LogX.d(TAG, "onCreate");
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction().add(R.id.my_framelayout, ArticleListFragment.newInstance("test123"), "tag1").commit();
        }
    }

    @Override
    protected void onStart() {
        LogX.d(TAG, "onStart BEGIN");
        super.onStart();
        LogX.d(TAG, "onStart END");
    }

    @Override
    protected void onResume() {
        LogX.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogX.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogX.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogX.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
