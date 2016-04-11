package com.eiwana.hellorx;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eiwana.hellorx.adapter.SampleAdapter;
import com.eiwana.hellorx.entity.Sample;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends RxBaseActivity {

    private final static String ACTION_RX_DEMO = "com.eiwana.intent.action.RX_DEMO";
    private final static String CATEGORY_RX_DEMO = "com.eiwana.intent.category.RX_DEMO";

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {

        mRecyclerView = find(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

        Observable.defer(() -> Observable.just(getSamples()))
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(samples -> mRecyclerView.setAdapter(new SampleAdapter(samples)));

    }

    private List<Sample> getSamples() {
        List<Sample> samples = new ArrayList<>();
        PackageManager pm = getPackageManager();
        Intent mainIntent = new Intent(ACTION_RX_DEMO, null);
        mainIntent.addCategory(CATEGORY_RX_DEMO);
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
        if (list == null) return samples;
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo info = list.get(i);
            String label = info.activityInfo.name;
            label = label.substring(label.lastIndexOf(".") + 1);
            if (label.endsWith("Activity")) {
                label = label.substring(0, label.lastIndexOf("Activity"));
            }
            samples.add(new Sample(label, info.activityInfo.name));
        }
        return samples;
    }
}
