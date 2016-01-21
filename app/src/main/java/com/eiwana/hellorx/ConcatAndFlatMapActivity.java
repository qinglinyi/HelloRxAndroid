package com.eiwana.hellorx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ConcatAndFlatMapActivity extends AppCompatActivity {

    public static final String TAG = "ConcatAndFlatMap";
    private TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        simpleTv = (TextView) findViewById(R.id.simpleTv);
        final DataManager dataManager = new DataManager();
        dataManager
                .getNumbers()
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return dataManager.squareOf(integer);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, integer + "\n");
                    }
                });
        dataManager
                .getNumbers()
                .concatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return dataManager.squareOf(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, integer + "\n");
                        simpleTv.append( integer + "\n");
                    }
                });
    }

    public static class DataManager {
        private final List<Integer> numbers;
        private final Executor jobExecutor;

        public DataManager() {
            this.numbers = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
            jobExecutor = Executors.newFixedThreadPool(5);
        }

        public Observable<Integer> getNumbers() {
            return Observable.from(numbers);
        }

        public List<Integer> getNumbersSync() {
            return this.numbers;
        }

        public Observable<Integer> squareOf(int number) {
            return Observable.just(number * number).subscribeOn(Schedulers.from(this.jobExecutor));
        }
    }

}
