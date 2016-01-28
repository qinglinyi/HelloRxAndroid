package com.eiwana.hellorx;

import android.os.Bundle;

import java.util.Arrays;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

//http://www.grokkingandroid.com/rxjavas-side-effect-methods/
public class SideEventMethodActivity extends BaseActivity {

    // android 中可以使用Frodo进行日志
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_event_method);
        displayHomeAsUpEnabled(true);


        Observable<String> someObservable = Observable
                .from(Arrays.asList(new Integer[]{2, 3, 5, 7, 11}))
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer o) {System.out.println("==after from==" + o);}
                })
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer prime) {return prime % 2 == 1;}
                })
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer o) {System.out.println("==after filter==" + o);}
                })
                .count()
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer o) {System.out.println("==after count==" + o);}
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer number) {
                        return String.format("Contains %d elements", number);
                    }
                });

        someObservable.subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println(throwable.toString());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Completed!");
                    }
                });

    }

}
