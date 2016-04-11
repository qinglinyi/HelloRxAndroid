package com.eiwana.hellorx.entity;

import android.content.Context;
import android.content.Intent;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class Sample {
    private String label;
    private String sampleClassName;

    public Sample(String label, String activityName) {
        this.sampleClassName = activityName;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSampleClassName() {
        return sampleClassName;
    }

    public void setSampleClassName(String activityName) {
        this.sampleClassName = activityName;
    }

    /**
     * 跳到对应的例子Activity
     *
     * @param context Context
     */
    public void go(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, sampleClassName);
        context.startActivity(intent);
    }
}
