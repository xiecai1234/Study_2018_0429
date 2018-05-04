package com.example.xiecaibao.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiecaibao.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeTestActivity extends AppCompatActivity {
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn4, R.id.btn3, R.id.btn2, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn4:
                break;
            case R.id.btn3:
                break;
            case R.id.btn2:
                break;
            case R.id.btn1:
                Toast.makeText(ButterKnifeTestActivity.this, "clcik btn1", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
