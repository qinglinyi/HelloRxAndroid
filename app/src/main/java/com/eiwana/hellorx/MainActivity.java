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
        }
    }
}
