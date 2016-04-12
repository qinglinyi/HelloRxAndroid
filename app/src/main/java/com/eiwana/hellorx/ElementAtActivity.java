package com.eiwana.hellorx;

import android.os.Bundle;

import rx.Observable;

public class ElementAtActivity extends SimpleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        elementAt(0);
        textView.append("================\n");
        elementAtOrDefault(10);
    }

    // 获取第n个元素，但是不能超过便捷
    private void elementAt(int index) {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .compose(bindToLifecycle())
                .elementAt(index)
                .subscribe(integer -> textView.append(integer + "\n"));
    }

    // 超过的话，使用默认值
    private void elementAtOrDefault(int index) {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .compose(bindToLifecycle())
                .elementAtOrDefault(index, -1)
                .subscribe(integer -> textView.append(integer + "\n"));
    }
}
