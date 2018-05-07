package com.example.xiecaibao.study.jnitest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.xiecaibao.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JniTestActivity extends AppCompatActivity {
    @BindView(R.id.tv_jni)
    TextView tvJni;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        ButterKnife.bind(this);

        tvJni.setText(Hello.getFromC());
    }
}
