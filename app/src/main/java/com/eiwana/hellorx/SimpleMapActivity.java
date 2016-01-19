package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class SimpleMapActivity extends BaseActivity {

    private TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        displayHomeAsUpEnabled(true);
        simpleTv = (TextView) findViewById(R.id.simpleTv);

        // map 对对象进行变换

        // 变化 1
        Observable
                .just("Hello RxAndroid!!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "--尾巴";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        simpleTv.setText(s);
                    }
                });

        Observable
                .just("Hello RxAndroid!!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.length();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        ((TextView) findViewById(R.id.simpleTv2)).setText("长度：" + s);
                    }
                });
    }
}
