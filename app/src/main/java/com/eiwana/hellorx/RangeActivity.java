package com.eiwana.hellorx;

import android.os.Bundle;

import rx.Observable;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class RangeActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        range();
    }

    private void range() {

        Observable.range(1, 6).subscribe(this::println);

    }
}
