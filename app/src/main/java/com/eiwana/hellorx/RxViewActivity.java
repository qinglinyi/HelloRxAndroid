package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class RxViewActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_view);
        displayHomeAsUpEnabled(true);

        Button btn = (Button) findViewById(R.id.btn);
        Button btn2 = (Button) findViewById(R.id.btn2);

        RxView.clicks(btn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(RxViewActivity.this, "1 Click!!", Toast.LENGTH_SHORT).show();
            }
        });

        RxView.clicks(btn2)
                .throttleFirst(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(RxViewActivity.this, "2 Click!!", Toast.LENGTH_SHORT).show();
                    }
                });


    }


}
