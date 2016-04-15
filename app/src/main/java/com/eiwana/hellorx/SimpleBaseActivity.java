package com.eiwana.hellorx;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleBaseActivity extends RxBaseActivity {

    TextView textView;
    ViewGroup contentView;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);
        displayHomeAsUpEnabled(true);
        textView = find(R.id.textView);
        contentView = find(R.id.contentView);
        mButton = find(R.id.btn);
    }

    protected void print(String append) {
        textView.append(append);
    }

    protected void println(Object append) {
        textView.append(append.toString() + "\n");
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
