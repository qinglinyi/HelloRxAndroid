package com.eiwana.hellorx;

import android.os.Bundle;

import rx.Observable;
import rx.Subscriber;

public class SimpleObservableActivity extends SimpleBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        displayHomeAsUpEnabled(true);


        Observable<String> simpleObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxAndroid!!");
            }
        });

        simpleObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                textView.setText(s);
            }
        });
    }


}
