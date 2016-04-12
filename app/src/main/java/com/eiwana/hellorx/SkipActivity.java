package com.eiwana.hellorx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import rx.Observable;

public class SkipActivity extends SimpleBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        skip(2);
        textView.append("===============\n");
        skipLast(2);
    }

    private void skip(int count) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .compose(bindToLifecycle())
                .skip(count)
                .subscribe(integer -> textView.append(String.valueOf(integer) + "\n"));
    }

    private void skipLast(int count) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .compose(bindToLifecycle())
                .skipLast(count)
                .subscribe(integer -> textView.append(String.valueOf(integer) + "\n"));
    }

}
