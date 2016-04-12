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
        takeLast(2);
    }

    private void take(int count) {
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .take(count)
                .subscribe(integer -> textView.append(String.valueOf(integer) + "\n"));
    }
    private void takeLast(int count) {
        Observable.just(1, 2, 3, 4, 5)
                .compose(bindToLifecycle())
                .takeLast(count)
                .subscribe(integer -> textView.append(String.valueOf(integer) + "\n"));
    }
}
