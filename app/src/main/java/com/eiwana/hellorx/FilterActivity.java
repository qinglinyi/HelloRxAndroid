package com.eiwana.hellorx;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class FilterActivity extends BaseActivity {

    TextView simpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        simpleTv = (TextView) findViewById(R.id.simpleTv);
        displayHomeAsUpEnabled(true);
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("");
        list.add("C");
        list.add(null);
        list.add("D");

        Observable
                .from(list)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        simpleTv.append(s + "\n");
                    }
                });
    }


}
