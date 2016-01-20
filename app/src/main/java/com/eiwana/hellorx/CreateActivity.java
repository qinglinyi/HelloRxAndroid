package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class CreateActivity extends BaseActivity {

    private TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        displayHomeAsUpEnabled(true);
        simpleTv = (TextView) findViewById(R.id.simpleTv);

        final String helloWorld = "Hello world!!";
        final String hi = "hi";
        final String world = "world";
        // 1
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(helloWorld);
                subscriber.onNext(hi);
                subscriber.onNext(world);
                subscriber.onCompleted();
            }
        });

        simpleTv.append("=========1========\n");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                simpleTv.append(s);
                simpleTv.append("\n");
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                simpleTv.append("====onCompleted===\n");
            }
        });
        simpleTv.append("=========1========\n");

        // 2
        observable = Observable.just(helloWorld, hi, world);

        simpleTv.append("=========2========\n");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                simpleTv.append(s);
                simpleTv.append("\n");
            }
        });
        simpleTv.append("=========2========\n");

        // 3
        observable = Observable.from(new String[]{helloWorld, hi, world});
        simpleTv.append("=========3========\n");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                simpleTv.append(s);
                simpleTv.append("\n");
            }
        });
        simpleTv.append("=========3========\n");
    }


}
