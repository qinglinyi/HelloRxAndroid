package com.eiwana.hellorx;

import android.os.Bundle;
import android.widget.TextView;

public class SimpleBaseActivity extends RxBaseActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);
        displayHomeAsUpEnabled(true);
        textView = find(R.id.textView);
    }

}
