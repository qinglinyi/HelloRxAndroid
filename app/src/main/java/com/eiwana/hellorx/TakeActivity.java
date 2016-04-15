package com.eiwana.hellorx;

import android.os.Bundle;

import rx.Observable;

/**
 * 1. take去列表的前几个
 * 2. takeLast 取列表的后几个
 */
public class TakeActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        take(2);
        print("========\n");
        takeLast(2);
        print("========\n");
        takeFirst(2);
        print("========\n");
        last();
    }

    // take去列表的前几个
    private void take(int count) {
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .take(count)
                .subscribe(integer -> print(String.valueOf(integer) + "\n"));
    }

    // takeLast 取列表的后几个
    private void takeLast(int count) {
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .takeLast(count)
                .subscribe(integer -> print(String.valueOf(integer) + "\n"));
    }

    // 满足条件的 第一个。其实就是一个过滤(filter)之后的take(1)
    private void takeFirst(int num) {
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .takeFirst(integer -> integer > num)
                .subscribe(integer -> print(String.valueOf(integer) + "\n"));
    }

    private void last(){
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .last()
                .subscribe(integer -> print(String.valueOf(integer) + "\n"));
    }



}
