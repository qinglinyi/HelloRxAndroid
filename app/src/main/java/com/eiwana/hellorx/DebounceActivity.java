package com.eiwana.hellorx;

import android.os.Bundle;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class DebounceActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        debounce();
    }

    int clickTimes;

    private void debounce() {

        RxView.clicks(mButton)
                .compose(bindToLifecycle())
                .map((Func1<Void, Void>) aVoid -> {
                    clickTimes++;
                    return null;
                })
                .throttleWithTimeout(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> System.out.println("结束"))
                .doOnUnsubscribe(() -> System.out.println("==doOnUnsubscribe=="))
                .subscribe(voids -> {println("点击" + clickTimes);});
    }


}
