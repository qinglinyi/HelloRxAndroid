package com.eiwana.hellorx;

import android.os.Bundle;

import rx.Observable;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class MergeWithActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        merge();
    }

    private void merge() {
        Observable<Integer> o1 = Observable.just(1, 2, 3, 4, 5, 6);
        Observable<Integer> o2 = Observable.just(1, 2, 13, 14, 15, 16);
        o1.mergeWith(o2).subscribe(integer -> print(integer + "\n"));
    }
}
