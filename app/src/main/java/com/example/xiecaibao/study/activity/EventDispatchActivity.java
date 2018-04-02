package com.example.xiecaibao.study.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.mvp.view.UserLoginActivity;
import com.example.xiecaibao.study.myview.MyLayout;
import com.example.xiecaibao.study.photo.AdvancedPhotoActivity;
import com.example.xiecaibao.study.photo.PhotoActivity;
import com.example.xiecaibao.study.utils.LogX;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class EventDispatchActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initView();
        init();
    }

    private void init() {
        setContentView(R.layout.view_event_dispatch);
        MyLayout myLayout = findViewById(R.id.my_layout);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogX.d("TAG", "myLayout on touch");
                return false;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogX.d("TAG", "You clicked button1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogX.d("TAG", "You clicked button2");
            }
        });
    }

    @SuppressLint("NewApi")
    private void initView() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button btn_dispatch = new Button(this);
        btn_dispatch.setText("btn_dispatch");
        btn_dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogX.d(TAG, "onclick");
            }
        });
        btn_dispatch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogX.d(TAG, "onTouch,action is:" + event.getAction());
                return false;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getDrawable(R.drawable.ic_launcher));
        imageView.setClickable(true);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogX.d(TAG, "onTouch,action is:" + event.getAction());
                return false;
            }

        });




        layout.addView(btn_dispatch);
        layout.addView(imageView);
        setContentView(layout);
    }

}
