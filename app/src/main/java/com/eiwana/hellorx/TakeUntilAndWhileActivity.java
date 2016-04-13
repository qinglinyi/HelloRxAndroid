package com.eiwana.hellorx;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class TakeUntilAndWhileActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        takeUntil(2);
        //        takeUntil2();
        takeWhile();
    }

    // 满足条件完成（退出）
    private void takeUntil(int num) {
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .takeUntil(integer -> integer > num)
                .doOnCompleted(() -> append("=OnCompleted=\n"))
                .subscribe(integer -> append(String.valueOf(integer) + "\n"));
    }

    // 第二个Observable开始执行的时候当前Observable结束
    private void takeUntil2() {

        Observable<String> observable = Observable.defer(this::getString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        observable.subscribe(s -> append(s + "\n"));

        Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .takeUntil(observable)
                .doOnCompleted(() -> append("=OnCompleted=\n"))
                .subscribe(integer -> append(String.valueOf(integer) + "\n"));
    }

    private Observable<String> getString() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Observable.just("A", "B");
    }

    // 不满足添加结束
    private void takeWhile() {
        Observable.just(1, 2, 3, 4)
                .takeWhile(integer -> integer != 3)
                .subscribe(integer -> append(String.valueOf(integer) + "\n"));
    }

}
