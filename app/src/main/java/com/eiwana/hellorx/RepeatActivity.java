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
public class RepeatActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        repeat();

        repeatWhen();
    }

    private void repeat() {
        Observable.just(1, 2)
                .compose(bindToLifecycle())
                .repeat(3)
                .subscribe(this::println);
    }

    private void repeatWhen() {
        Observable.just(1, 2)
                .compose(bindToLifecycle())
                .repeatWhen(observable -> observable.delay(3, TimeUnit.SECONDS), Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::println);
    }
}
