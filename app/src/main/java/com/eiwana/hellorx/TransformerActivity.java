package com.eiwana.hellorx;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class TransformerActivity extends BaseActivity {

    public static final String alphabet = "abcdefghijklmnopqrstuvwsyz";

    private TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        displayHomeAsUpEnabled(true);
        simpleTv = (TextView) findViewById(R.id.simpleTv);


        Observable.Transformer<Integer, String> transformer = new MyTransformer();

        Observable
                .just(1, 2, 3, 40, 21, 20, 11)
                .compose(transformer)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        simpleTv.append(s + "\n");
                    }
                });

        Observable
                .just(1, 2, 3, 40, 21, 20, 11)
                .compose(transformer)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("TransformerActivity", s);
                    }
                });

    }


    public class MyTransformer implements Observable.Transformer<Integer, String> {
        @Override
        public Observable<String> call(Observable<Integer> observable) {
            return observable
                    .filter(new Func1<Integer, Boolean>() {
                        @Override
                        public Boolean call(Integer integer) {
                            return integer >= 0 && integer < alphabet.length();
                        }
                    })
                    .toSortedList()
                    .flatMap(new Func1<List<Integer>, Observable<Integer>>() {
                        @Override
                        public Observable<Integer> call(List<Integer> integers) {
                            return Observable.from(integers);
                        }
                    })
                    .map(new Func1<Integer, String>() {
                        @Override
                        public String call(Integer integer) {
                            return integer + "->" + String.valueOf(alphabet.charAt(integer));
                        }
                    })
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            return s.toUpperCase();
                        }
                    });

        }
    }


}
