package com.eiwana.hellorx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eiwana.hellorx.R;
import com.eiwana.hellorx.entity.Sample;

import java.util.List;

/**
 * @author qinglinyi
 * @since 1.0.0
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {

    private List<Sample> samples;
    private Context context;

    public SampleAdapter(List<Sample> samples) {
        this.samples = samples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_sample, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Sample sample = samples.get(position);
        holder.sampleTv.setText(sample.getLabel());
        holder.sampleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample.go(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return samples == null ? 0 : samples.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView sampleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            sampleTv = (TextView) itemView;
        }
    }
}
