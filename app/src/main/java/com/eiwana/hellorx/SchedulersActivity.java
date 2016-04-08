package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class SchedulersActivity extends BaseActivity {

    TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        displayHomeAsUpEnabled(true);
        simpleTv = (TextView) findViewById(R.id.simpleTv);

        final Action1<String> next = new Action1<String>() {
            @Override
            public void call(String s) {
                simpleTv.append(s + "\n");
            }
        };

        final Action1<Throwable> error = new Action1<Throwable>() {
            @Override
            public void call(Throwable s) {
                System.out.println("==error===");
            }
        };

        final Action0 complete = new Action0() {
            @Override
            public void call() {
                System.out.println("==complete==");
            }
        };

        // 使用Schedulers设置异步操作
        final Subscription sub = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //                subscriber.add(Observable.just(1).subscribe(new Action1<Integer>() {
                //                    @Override
                //                    public void call(Integer integer) {
                //                        System.out.println("===add===ok==="+integer);
                //                    }
                //                }));

                // add 就是将新的Subscription的事件（B）绑到一起，如果当前Subscription(A) unsubscribe，
                // 那么这个也unsubscribe。意思就是说如果B添加到A,A调用unsubscribe，那么B也unsubscribe

                // Subscriptions.create的参数是一个在Subscription unsubscribe的时候调用的Action
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("==ok==");
                    }
                }));

                subscriber.onNext("Hello~~~~");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("World~~~~");
                subscriber.onCompleted();

            }
        }).observeOn(AndroidSchedulers.mainThread())// 回调在主线程中
                .subscribeOn(Schedulers.io())// 执行在io线程中
                .subscribe(next, error, complete);// OnSubscribe的Call方法是在subscribe的时候执行的

        // 延迟执行，在2s后执行传入一个0，也可以传入指定数字
        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        sub.unsubscribe();
                    }
                });


        // 堵塞
        //==================
        // Observable.from(getHelloWorld(1))
        //      .observeOn(AndroidSchedulers.mainThread())
        //      .subscribeOn(Schedulers.io())
        //      .next(next);
        //==================
        // 不堵塞 使用defer包装费时的操作，使得from操作延迟到绑定subscriber。这样不会堵塞。
        Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.from(getHelloWorld(1));
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(next);

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
