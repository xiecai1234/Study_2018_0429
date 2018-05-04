package com.example.xiecaibao.study.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.utils.LogX;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTestFirstActivity extends AppCompatActivity {
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.textView)
    TextView textView;
    private String TAG = "xcb";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        textView.setText("EventBusTestFirstActivity");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        LogX.d(TAG, "messageEvent.getMessage() is:" + messageEvent.getMessage());
        textView.setText(messageEvent.getMessage());
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
                startActivity(new Intent(EventBusTestFirstActivity.this, EventBusTestSecondActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
