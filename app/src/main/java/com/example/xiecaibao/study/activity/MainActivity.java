package com.example.xiecaibao.study.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.mvp.view.UserLoginActivity;
import com.example.xiecaibao.study.photo.AdvancedPhotoActivity;
import com.example.xiecaibao.study.photo.PhotoActivity;
import com.example.xiecaibao.study.utils.LogX;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
//            }
//        });
//        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, TraceActivity.class));
//            }
//        });
//        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
//            }
//        });
//        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, RxJavaTestActivity.class));
//            }
//        });

//        test1();
//        test2();

        initView();
    }

    private void initView() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button btn1 = new Button(this);
        btn1.setText("fragmenttest");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyTestFragmentActivity.class));
            }
        });
        layout.addView(btn1);

        Button btn_mvp = new Button(this);
        btn_mvp.setText("mvp");
        btn_mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
            }
        });
        layout.addView(btn_mvp);

        Button btn_test_weight = new Button(this);
        btn_test_weight.setText("btn_test_weight");
        btn_test_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestWeightActivity.class));
            }
        });
        layout.addView(btn_test_weight);


        Button btn_largeImage = new Button(this);
        btn_largeImage.setText("largeImage");
        btn_largeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LargeImageActivity.class));
            }
        });
        layout.addView(btn_largeImage);

        Button btn_loadphoto = new Button(this);
        btn_loadphoto.setText("photo");
        btn_loadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhotoActivity.class));
            }
        });
        layout.addView(btn_loadphoto);


        Button btn_testbitmap = new Button(this);
        btn_testbitmap.setText("testbitmap");
        btn_testbitmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestImageActivity.class));
            }
        });
        layout.addView(btn_testbitmap);

        Button btn_photo2 = new Button(this);
        btn_photo2.setText("Lrucach+Diskcach");
        btn_photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdvancedPhotoActivity.class));
            }
        });
        layout.addView(btn_photo2);

        Button btn_news_viewpager = new Button(this);
        btn_news_viewpager.setText("btn_news_viewpager");
        btn_news_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyViewPager.class));
            }
        });
        layout.addView(btn_news_viewpager);

        Button btn_event_dispatch = new Button(this);
        btn_event_dispatch.setText("btn_event_dispatch");
        btn_event_dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventDispatchActivity.class));
            }
        });
        layout.addView(btn_event_dispatch);

        setContentView(layout);
    }

    private void test1() {
        Observable.just("hello").subscribe(new Consumer<String>() {
            // 每次接收到Observable的事件都会调用Consumer.accept（）
            @Override public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    private void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                LogX.d(TAG, "开始采用subscribe连接");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                LogX.d(TAG, "call onNext, values is:" + value);
                if (2 == value) {
                    mDisposable.dispose();
                    boolean isDisposed = mDisposable.isDisposed();
                    LogX.d(TAG, "已经切断了连接：" + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogX.d(TAG, "call onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogX.d(TAG, "call onComplete");
            }
        });
    }
}
