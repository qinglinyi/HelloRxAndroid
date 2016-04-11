package com.eiwana.hellorx;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subscriptions.CompositeSubscription;

public class SubjectActivity extends BaseActivity {

    //subject是一个神奇的对象，它可以是一个Observable同时也可以是一个Observer：它作为连接这两个世界的一座桥梁。
    // 一个Subject可以订阅一个Observable，就像一个观察者，并且它可以发射新的数据，或者传递它接受到的数据，
    // 就像一个Observable。很明显，作为一个Observable，观察者们或者其它Subject都可以订阅它。

    // 一旦Subject订阅了Observable，它将会触发Observable开始发射。如果原始的Observable是“冷”的，
    // 这将会对订阅一个“热”的Observable变量产生影响。

    //RxJava提供四种不同的Subject：
    //    PublishSubject
    //    BehaviorSubject
    //    ReplaySubject
    //    AsyncSubject


    private TextView textView;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        textView = (TextView) findViewById(R.id.textView);
        // publishSubject();
         behaviorSubject();
        //        replaySubject();
//        asyncSubject();


    }

    private void publishSubject() {
        final PublishSubject<String> subject = PublishSubject.create();
        Subscription subscribe = subject.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("==subscribe==");
                textView.setText(s);
                Log.e(getClass().getSimpleName(), "=======PublishSubject===" + s);
            }
        });

        subject.onNext("Hello~~");// 会触发subscribe的call
        //        subscribe.unsubscribe();
        //        subject.onCompleted();
        Subscription timer = Observable
                .timer(6, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("==timer==");
                        subject.onNext("GoodBye~~");// 没unsubscribe或者onCompleted的话，还是会会触发subscribe的call
                    }
                });

        compositeSubscription.add(subscribe);
        compositeSubscription.add(timer);

    }

    private void behaviorSubject() {
        BehaviorSubject<Integer> subject = BehaviorSubject.create(1);// 不发生错误的时候能拿到前一次onNext的值
        subject.onNext(2);
        subject.onNext(3);
        Subscription sb1 = subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                textView.setText(String.valueOf(integer));
                System.out.println(integer);
            }
        });

        Subscription sb2 = subject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer + "====2===");
            }
        });

        subject.onNext(4);
        subject.onNext(5);
        sb1.unsubscribe();//1 不接收，2还接收
        subject.onNext(6);
        subject.onCompleted();// 2 也不接收了
        subject.onNext(7);


    }

    //ReplaySubject会缓存它所订阅的所有数据,向任意一个订阅它的观察者重发
    private void replaySubject() {
        ReplaySubject<Integer> replaySubject = ReplaySubject.create(1);
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        replaySubject.onNext(4);
        replaySubject.onNext(5);
        replaySubject.onNext(6);
        replaySubject.onCompleted();
        replaySubject.onNext(7);
        replaySubject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                textView.setText("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
                textView.setText(String.valueOf(integer));
            }
        });
        replaySubject.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                textView.setText("onCompleted2");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer + "==2");
                textView.setText(String.valueOf(integer) + "==2");
            }
        });
    }

    public void asyncSubject() {
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.onNext(1);
        subject.onNext(2);
        subject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
        subject.onNext(3);
        subject.onNext(4);
        subject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("2===" + integer);
            }
        });
        subject.onNext(5);
        subject.onCompleted();
        subject.onNext(6);

        // 两个都只接收5

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}
