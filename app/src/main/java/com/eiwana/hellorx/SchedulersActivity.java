package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class SchedulersActivity extends BaseActivity {

    TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        displayHomeAsUpEnabled(true);
        simpleTv = (TextView) findViewById(R.id.simpleTv);

        final Action1<String> subscribe = new Action1<String>() {
            @Override
            public void call(String s) {
                simpleTv.append(s + "\n");
            }
        };

        // 使用Schedulers设置异步操作
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("Hello~~~~");
                subscriber.onNext("World~~~~");
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())// 回调在主线程中
                .subscribeOn(Schedulers.io())// 执行在io线程中
                .subscribe(subscribe);

        // 堵塞
        //==================
        // Observable.from(getHelloWorld(1))
        //      .observeOn(AndroidSchedulers.mainThread())
        //      .subscribeOn(Schedulers.io())
        //      .subscribe(subscribe);
        //==================
        // 不堵塞 使用defer包装费时的操作
        Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.from(getHelloWorld(1));
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscribe);


    }

    private String[] getHelloWorld(int index) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new String[]{"Hello~~~~" + index, "World~~~~" + index};
    }


}
