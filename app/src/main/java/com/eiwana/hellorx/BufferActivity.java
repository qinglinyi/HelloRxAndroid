package com.eiwana.hellorx;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class BufferActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        buffer3();
        //        buffer4(3);
    }

    private void buffer() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(2)
                .subscribe(this::println);
    }

    private void buffer2() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(2, 3)
                .subscribe(this::println);
    }

    private void buffer3() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(1, TimeUnit.SECONDS, Schedulers.io())
                .subscribe((append) -> {
                    System.out.println(Thread.currentThread().getName());
                    println(append);
                });
    }

    private void buffer4(int second) {

        Observable.defer((Func0<Observable<Object>>) () -> Observable.just((int) (Math.random() * 20)))
                .repeatWhen(observable -> observable.delay(1, TimeUnit.SECONDS), Schedulers.io())
                .compose(bindToLifecycle())
                //                .observeOn(AndroidSchedulers.mainThread())// 在这里的时候 subscriber的不是这个线程
                .buffer(second, TimeUnit.SECONDS)// 这个线程有讲究，当前Observable如果在子线程中的，buffer回来还是在子线程
                // second秒取一次 ,// 默认情况下会使用computation调度器。
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> System.out.println("结束"))
                .doOnUnsubscribe(() -> System.out.println("==doOnUnsubscribe=="))
                .subscribe((append) -> {
                            System.out.println(Thread.currentThread().getName());
                            println(append);
                        },
                        this::println);
    }


    //buffer(boundary)  ??

    //buffer(bufferClosingSelector) ??

}
