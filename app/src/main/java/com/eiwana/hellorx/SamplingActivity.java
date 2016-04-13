package com.eiwana.hellorx;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class SamplingActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable<Integer> sensor = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        sensor.sample(1, TimeUnit.SECONDS)
                .subscribe(integer -> textView.append(integer + "\n"));
    }
}
