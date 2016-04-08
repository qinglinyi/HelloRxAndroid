package com.eiwana.hellorx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simpleObservableBtn:
                startActivity(new Intent(this, SimpleObservableActivity.class));
                break;
            case R.id.simpleSubscriberBtn:
                startActivity(new Intent(this, SimpleSubscriberActivity.class));
                break;
            case R.id.simpleMapBtn:
                startActivity(new Intent(this, SimpleMapActivity.class));
                break;
            case R.id.createBtn:
                startActivity(new Intent(this, CreateActivity.class));
                break;
            case R.id.simpleSchedulerBtn:
                startActivity(new Intent(this, SimpleSchedulerActivity.class));
                break;
            case R.id.flatMapBtn:
                startActivity(new Intent(this, FlatMapActivity.class));
                break;
            case R.id.filterBtn:
                startActivity(new Intent(this, FilterActivity.class));
                break;
            case R.id.concatAndFlatMapBtn:
                startActivity(new Intent(this, ConcatAndFlatMapActivity.class));
                break;
            case R.id.rxViewBtn:
                startActivity(new Intent(this, RxViewActivity.class));
                break;
            case R.id.schedulersBtn:
                startActivity(new Intent(this, SchedulersActivity.class));
                break;
            case R.id.transformerBtn:
                startActivity(new Intent(this, TransformerActivity.class));
                break;
            case R.id.sideEventMethodBtn:
                startActivity(new Intent(this, SideEventMethodActivity.class));
                break;
            case R.id.intervalBtn:
                startActivity(new Intent(this, IntervalActivity.class));
                break;
            case R.id.subjectBtn:
                startActivity(new Intent(this, SubjectActivity.class));
                break;
        }
    }
}
