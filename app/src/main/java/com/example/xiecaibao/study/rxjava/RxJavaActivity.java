package com.example.xiecaibao.study.rxjava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.xiecaibao.study.utils.LogX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "xcb";
    Observable mObservable;
    Observer mObserver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildDefault();
        initView();
    }

    private void initView() {
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(layout);
        setContentView(scrollView);

        Button btn_test1 = new Button(this);
        btn_test1.setText("test1");
        btn_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testMap();
//                testBuffer();
//                testConcat();
//                testMerge();
//                concatDelayError2();
                retry();
            }

        });
        layout.addView(btn_test1);
    }

    private void buildDefault() {
        mObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                LogX.d(TAG, "subscribe");
                emitter.onNext(1L);
                emitter.onNext(2L);
                emitter.onComplete();
            }
        });

        mObserver = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogX.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long o) {
                LogX.d(TAG, "onNext:" + o);
            }

            @Override
            public void onError(Throwable e) {
                LogX.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogX.d(TAG, "onComplete");
            }
        };
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(mObserver);
    }

    private void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })/*.map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) throws Exception {
                return "使用 Map变换操作符 将事件" + integer +"的参数从 整型"+integer + " 变换成 字符串类型" + integer ;
            }
        })*/.concatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {

                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                    // 最终合并，再发送给被观察者
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG, o.toString());
            }
        });
    }

    private void testBuffer() {
        // 设置缓存区大小 & 步长
        // 缓存区大小 = 每次从被观察者中获取的事件数量
        // 步长 = 每次获取新事件的数量

        Observable.just(1, 2, 3, 4, 5).buffer(3, 1)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> stringList) {

                        Log.d(TAG, " 缓存区里的事件数量 = " + stringList.size());
                        for (Integer value : stringList) {
                            Log.d(TAG, " 事件 = " + value);
                        }
                        Log.d(TAG, "--------------");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void testConcat() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6), Observable.just(7, 8, 9)).subscribe(mObserver);
    }

    private void testMerge() {
        Observable.merge(Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS, Schedulers.trampoline()),
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS, Schedulers.trampoline()))
                .subscribe(mObserver);
    }

    private void concatDelayError1() {
        Observable.concat(Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                emitter.onNext(1l);
                emitter.onNext(2l);
                emitter.onNext(3l);
                emitter.onError(new NullPointerException());
                // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
                emitter.onComplete();

            }
        }), Observable.just(4, 5, 6), Observable.just(7, 8, 9)).subscribe(mObserver);
    }

    private void concatDelayError2() {
        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                emitter.onNext(1l);
                emitter.onNext(2l);
                emitter.onNext(3l);
                emitter.onError(new NullPointerException());
                // 发送Error事件，因为无使用concatDelayError，所以第2个Observable将不会发送事件
                emitter.onComplete();

            }
        }), Observable.just(4l, 5l, 6l), Observable.just(7l, 8l, 9l)).subscribe(mObserver);
    }

    private void zip1() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "被观察者1发送了事件1");
                emitter.onNext(1);
                // 为了方便展示效果，所以在发送事件后加入2s的延迟
                Thread.sleep(1000);
                Log.d(TAG, "被观察者1发送了事件2");
                emitter.onNext(2);
                Thread.sleep(1000);
                Log.d(TAG, "被观察者1发送了事件3");
                emitter.onNext(3);
                Thread.sleep(1000);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        // 设置被观察者1在工作线程1中工作<-- 创建第2个被观察者 -->
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "被观察者2发送了事件A");
                emitter.onNext("A");
                Thread.sleep(1000);
                Log.d(TAG, "被观察者2发送了事件B");
                emitter.onNext("B");
                Thread.sleep(1000);
                Log.d(TAG, "被观察者2发送了事件C");
                emitter.onNext("C");
                Thread.sleep(1000);
                Log.d(TAG, "被观察者2发送了事件D");
                emitter.onNext("D");
                Thread.sleep(1000);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());
        //设置被观察者2在工作线程2中工作// 假设不作线程控制，则该两个被观察者会在同一个线程中工作，即发送事件存在先后顺序，而不是同时发送
        // <-- 使用zip变换操作符进行事件合并 --> // 注：创建BiFunction对象传入的第3个参数 = 合并后数据的数据类型
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String string) throws Exception {
                return integer + string;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "最终接收到的事件 = " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    private void combineLatest() {

        Observable.combineLatest(Observable.just(1L, 2L, 3L),// 第1个发送数据事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long o1, Long o2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据// o2 = 第2个Observable发送的每1个数据
                        Log.e(TAG, "合并的数据是： " + o1 + " " + o2);
                        return o1 + o2; // 合并的逻辑 = 相加// 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
                Log.e(TAG, "合并的结果是： " + s);
            }
        });

    }

    private void reduce() {

        Observable.just(1, 2, 3, 4, 5, 6).reduce(new BiFunction<Integer, Integer, Integer>() {
            // 在该复写方法中复写聚合的逻辑
            // @Override
            public Integer apply(@NonNull Integer s1, @NonNull Integer s2) throws Exception {
                Log.e(TAG, "本次计算的数据是： " + s1 + " 乘 " + s2);
                return s1 * s2; // 本次聚合的逻辑是：全部数据相乘起来// 原理：第1次取前2个数据相乘，之后每次获取到的数据 = 返回的数据x原始下1个数据每
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer s) throws Exception {
                Log.e(TAG, "最终计算的结果是： " + s);
            }
        });
    }

    private void collect() {

        Observable.just(1, 2, 3, 4, 5, 6).collect( // 1. 创建数据结构（容器），用于收集被观察者发送的数据
                new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                    // 2. 对发送的数据进行收集
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> list, Integer integer) throws Exception { // 参数说明：list = 容器，integer = 后者数据
                        list.add(integer); // 对发送的数据进行收集
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(@NonNull ArrayList<Integer> s) throws Exception {
                Log.e(TAG, "本次发送的数据是： " + s);
            }
        });
    }

    private void startWith() {
        //在一个被观察者发送事件前，追加发送一些数据 --> // 注：追加数据顺序 = 后调用先追加
        Observable.just(4, 5, 6)
                .startWith(0) // 追加单个数据 = startWith()
                .startWithArray(1, 2, 3) // 追加多个数据 = startWithArray()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
        //在一个被观察者发送事件前，追加发送被观察者 & 发送数据 --> // 注：追加数据顺序 = 后调用先追加
        Observable.just(4, 5, 6).startWith(Observable.just(1, 2, 3)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "接收到了事件" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    private void delay() {
        Observable.just(1L, 2L, 3L).delay(3, TimeUnit.SECONDS).subscribe(mObserver);
    }

    private void scheduler() {
        mObservable.subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .subscribe(mObserver);
    }

    private void doXxx() {

        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                e.onNext(1L);
                e.onNext(2L);
                e.onNext(3L);
                e.onError(new Throwable("发生错误了"));
            }
        })
                // 1. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Long>>() {
                    @Override
                    public void accept(Notification<Long> integerNotification) throws Exception {
                        Log.d(TAG, "doOnEach: " + integerNotification.getValue());
                    }
                })
                // 2. 执行Next事件前调用
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        Log.d(TAG, "doOnNext: " + integer);
                    }
                })
                // 3. 执行Next事件后调用
                .doAfterNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        Log.d(TAG, "doAfterNext: " + integer);
                    }
                })
                // 4. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 6. 观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doAfterTerminate: ");
                    }
                })

                .onErrorReturn(new Function<Throwable, Long>() {
                    @Override
                    public Long apply(@NonNull Throwable throwable) throws Exception {
                        // 捕捉错误异常
                        Log.e(TAG, "在onErrorReturn处理了错误: " + throwable.toString());
                        return 666L; // 发生错误事件后，发送一个"666"事件，最终正常结束
                    }
                })

                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                }).subscribe(mObserver);
    }

    private void retry(){
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> e) throws Exception {
                e.onNext(1L);
                e.onNext(2L);
                e.onNext(3L);
                e.onError(new Throwable("发生错误了"));
            }
        })
                .retry(3)
                .subscribe(mObserver);
    }
}
