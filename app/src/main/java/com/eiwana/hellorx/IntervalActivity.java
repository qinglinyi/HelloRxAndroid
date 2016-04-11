package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class IntervalActivity extends BaseActivity {

    private TextView textView;
    private Subscription subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        textView = (TextView) findViewById(R.id.textView);

        // interval：指定时间间隔重复call 第一个数字的，第二个数字是间隔
        subscribe = Observable.interval(1, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println(String.valueOf(aLong));
                        textView.setText(String.valueOf(aLong));
                    }
                });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isUnsubscribed()) {
            subscribe.unsubscribe();
            subscribe = null;
        }
    }
}
