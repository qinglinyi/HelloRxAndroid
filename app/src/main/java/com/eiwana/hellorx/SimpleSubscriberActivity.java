package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;

public class SimpleSubscriberActivity extends BaseActivity {

    private TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        displayHomeAsUpEnabled(true);

        simpleTv = (TextView) findViewById(R.id.simpleTv);

        // 最初始
//        Observable<String> simpleObservable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello RxAndroid!!");
//            }
//        });
//
//        simpleObservable.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                simpleTv.setText(s);
//            }
//        });

        // 简化之后
//        Observable<String> simpleObservable = Observable.just("Hello RxAndroid!!");
//
//        simpleObservable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                simpleTv.setText(s);
//            }
//        });


        // 简化之后合并
        Observable
                .just("Hello RxAndroid!!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        simpleTv.setText(s);
                    }
                });
    }



}
