package com.mtach.bideshibazar.features;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    public interface OnFeatureClickListener {
        void onFeatureClick(FeatureItem item);
    }

    private Context context;
    private List<FeatureItem> featureList;
    private OnFeatureClickListener listener;

    public FeatureAdapter(Context context, List<FeatureItem> featureList, OnFeatureClickListener listener) {
        this.context = context;
        this.featureList = featureList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feature, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        FeatureItem item = featureList.get(position);
        holder.title.setText(item.getTitle());

        // Future: set icon if you want, e.g.
        // holder.icon.setImageResource(R.drawable.ic_feature);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFeatureClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    static class FeatureViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.featureTitle);
            icon  = itemView.findViewById(R.id.featureIcon);
        }
    }
}





