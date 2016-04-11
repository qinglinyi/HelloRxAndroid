package com.eiwana.hellorx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import rx.Observable;

/**
 * Distinct 就是去重
 * 1, 2, 3, 4, 5, 6, 1, 2, 3, 4 ==>
 * 1, 2, 3, 4, 5, 6
 */
public class DistinctActivity extends RxBaseActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distinct);
        displayHomeAsUpEnabled(true);

        textView = find(R.id.textView);
        FloatingActionButton fab = find(R.id.fab);

        assert fab != null;
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show());

        distinct();
    }


    private void distinct() {
        Observable.just(1, 2, 3, 4, 5, 6, 1, 2, 3, 4)
                .compose(bindToLifecycle())
                .distinct()
                .toList()
                .subscribe(integer -> textView.append(String.valueOf(integer) + "\n"));
    }


}
