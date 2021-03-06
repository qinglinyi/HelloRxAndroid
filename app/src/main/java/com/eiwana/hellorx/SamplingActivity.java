package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class SamplingActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sample();
        sampleUse();
    }

    private void sample() {
        Observable<Integer> sensor = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        sensor.sample(1, TimeUnit.SECONDS)
                .subscribe(this::println);
    }

    int clickTimes = 0;

    private void sampleUse() {

        Button mButton = new Button(this);
        mButton.setText("click");
        RxView.clicks(mButton)
                .compose(bindToLifecycle())
                .map((Func1<Void, Void>) aVoid -> {
                    clickTimes++;
                    return null;
                })
                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> System.out.println("结束"))
                .doOnUnsubscribe(() -> System.out.println("==doOnUnsubscribe=="))
                .subscribe(voids -> {println("点击" + clickTimes);});
        contentView.addView(mButton);

    }

}
