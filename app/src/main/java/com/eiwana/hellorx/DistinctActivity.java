package com.eiwana.hellorx;

import android.os.Bundle;

import rx.Observable;

/**
 * Distinct 就是去重
 * 1, 2, 3, 4, 5, 6, 1, 2, 3, 4 ==>
 * 1, 2, 3, 4, 5, 6
 */
public class DistinctActivity extends SimpleBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        append("去重\n");
        distinct();
    }


    private void distinct() {
        Observable.just(1, 2, 3, 4, 5, 6, 1, 2, 3, 4)
                .compose(bindToLifecycle())
                .distinct()
                .subscribe(integer -> append(String.valueOf(integer) + "\n"));
    }


}
