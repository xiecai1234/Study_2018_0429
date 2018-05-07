package com.example.xiecaibao.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.xiecaibao.study.eschool.EschoolActivity;
import com.example.xiecaibao.study.eschool.h5.H5;
import com.example.xiecaibao.study.eventbus.EventBusTestFirstActivity;
import com.example.xiecaibao.study.guide.GuideActivity;
import com.example.xiecaibao.study.jnitest.JniTestActivity;
import com.example.xiecaibao.study.mvp.view.UserLoginActivity;
import com.example.xiecaibao.study.okhttp3.OkhttpTestActivity;
import com.example.xiecaibao.study.photo.AdvancedPhotoActivity;
import com.example.xiecaibao.study.photo.GlideTestActivity;
import com.example.xiecaibao.study.photo.PhotoActivity;
import com.example.xiecaibao.study.rxjava.RxJavafixRetrofitActivity;
import com.example.xiecaibao.study.tab.TabActivity;
import com.example.xiecaibao.study.utils.LogX;
import com.example.xiecaibao.study.video.VideoActivity;
import com.example.xiecaibao.study.volley.VolleyTestActivity;

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

    private void initView() {
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(layout);
        setContentView(scrollView);

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

        Button btn_test_layout = new Button(this);
        btn_test_layout.setText("testlayout");
        btn_test_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestLayoutActivity.class));
            }
        });
        layout.addView(btn_test_layout);

        Button btn_leak = new Button(this);
        btn_leak.setText("MemoryLeak");
        btn_leak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LeakActivity.class));
            }
        });
        layout.addView(btn_leak);

        Button btn_rxjava = new Button(this);
        btn_rxjava.setText("RxJava");
        btn_rxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RxJavafixRetrofitActivity.class));
            }
        });
        layout.addView(btn_rxjava);

        Button btn_butterknife = new Button(this);
        btn_butterknife.setText("btn_butterknife");
        btn_butterknife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ButterKnifeTestActivity.class));
            }
        });
        layout.addView(btn_butterknife);

        Button btn_Volley = new Button(this);
        btn_Volley.setText("Volley");
        btn_Volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VolleyTestActivity.class));
            }
        });
        layout.addView(btn_Volley);

        Button btn_okhttp = new Button(this);
        btn_okhttp.setText("okhttp");
        btn_okhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OkhttpTestActivity.class));
            }
        });
        layout.addView(btn_okhttp);

        Button btn_eventbus = new Button(this);
        btn_eventbus.setText("EventBus");
        btn_eventbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventBusTestFirstActivity.class));
            }
        });
        layout.addView(btn_eventbus);

        Button btn_swipeback = new Button(this);
        btn_swipeback.setText("SwipeBack");
        btn_swipeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SwipeBackTestActivity.class));
            }
        });
        layout.addView(btn_swipeback);

        Button btn_thread_pool = new Button(this);
        btn_thread_pool.setText("ThreadPool");
        btn_thread_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThreadPoolTest.class));
            }
        });
        layout.addView(btn_thread_pool);

        Button btn_toolbar = new Button(this);
        btn_toolbar.setText("Toolbar");
        btn_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ToolbarTest.class));
            }
        });
        layout.addView(btn_toolbar);

        Button btn_school = new Button(this);
        btn_school.setText("School");
        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EschoolActivity.class));
            }
        });
        layout.addView(btn_school);

        Button btn_h5 = new Button(this);
        btn_h5.setText("h5");
        btn_h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, H5.class));
            }
        });
        layout.addView(btn_h5);

        Button btn_glide = new Button(this);
        btn_glide.setText("Glide");
        btn_glide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GlideTestActivity.class));
            }
        });
        layout.addView(btn_glide);

        Button btn_video = new Button(this);
        btn_video.setText("VideoView+MediaController");
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });
        layout.addView(btn_video);

        Button btn_tab= new Button(this);
        btn_tab.setText("TAB");
        btn_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TabActivity.class));
            }
        });
        layout.addView(btn_tab);

        Button btn_guide= new Button(this);
        btn_guide.setText("Guide");
        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GuideActivity.class));
            }
        });
        layout.addView(btn_guide);

        Button btn_jni= new Button(this);
        btn_jni.setText("JNI");
        btn_jni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, JniTestActivity.class));
            }
        });
        layout.addView(btn_jni);
    }


}
