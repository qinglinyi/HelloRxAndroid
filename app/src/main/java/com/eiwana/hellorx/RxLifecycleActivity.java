package com.eiwana.hellorx;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 预期：Subscription 在 Activity onDestroy的时候 unsubscribe
 *
 * 1. 从该例子开始使用lambda完成Observable
 * 2. RxBaseActivity 实现了RxLifecycle
 * 3. 从该例子开始使用RxBaseActivity,将不手动取消Subscription
 */
public class RxLifecycleActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .doOnUnsubscribe(() -> System.out.println("===RxLifecycleActivity==onUnsubscribe==="))
                .subscribe(aLong -> textView.setText(String.valueOf(aLong)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("===RxLifecycleActivity==onDestroy===");
    }
}
