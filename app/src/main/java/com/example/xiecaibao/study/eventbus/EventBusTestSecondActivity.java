package com.example.xiecaibao.study.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.xiecaibao.study.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusTestSecondActivity extends AppCompatActivity {
    @BindView(R.id.title_back_btn)
    ImageButton titleBackBtn;
    @BindView(R.id.title_textview)
    TextView titleTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_title);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.title_back_btn)
    public void onViewClicked() {
        EventBus.getDefault().post(new MessageEvent("EventBusTestSecondActivity"));
        finish();
    }
}
