package com.example.xiecaibao.study.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.utils.LogX;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/26.
 */

public class RxJavaTestActivity extends AppCompatActivity{
    private static final String TAG = "RxJavaTestActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_activity_set);
        test2();
    }

    private void test1() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogX.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                LogX.d(TAG, "call onNext, values is:" + value);
            }

            @Override
            public void onError(Throwable e) {
                LogX.d(TAG, "call onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogX.d(TAG, "call onComplete");
            }
        };

        observable.subscribe(observer);
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
