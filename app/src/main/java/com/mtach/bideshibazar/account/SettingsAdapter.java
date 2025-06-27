package com.mtach.bideshibazar.account;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;
import com.mtach.bideshibazar.features.CurrencySettingsActivity;
import com.mtach.bideshibazar.features.DeleteAccountActivity;
import com.mtach.bideshibazar.features.DeliverySettingsActivity;
import com.mtach.bideshibazar.features.FeedbackActivity;
import com.mtach.bideshibazar.features.LanguageSettingsActivity;
import com.mtach.bideshibazar.features.NotificationSettingsActivity;
import com.mtach.bideshibazar.features.ShareActivity;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {
    private List<SettingsItem> settingsList;
    private Context context;

    public SettingsAdapter(Context context, List<SettingsItem> settingsList) {
        this.context = context;
        this.settingsList = settingsList;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_settings, parent, false);
        return new SettingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        SettingsItem item = settingsList.get(position);
        holder.title.setText(item.getTitle());

        // Show label if available
        if (item.getLabel() != null && !item.getLabel().isEmpty()) {
            holder.label.setText(item.getLabel());
            holder.label.setVisibility(View.VISIBLE);
        } else {
            holder.label.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = null;
            switch (item.getTitle()) {
                case "Language":
                    intent = new Intent(context, LanguageSettingsActivity.class); break;
                case "Notifications":
                    intent = new Intent(context, NotificationSettingsActivity.class); break;
                case "Delete account":
                    intent = new Intent(context, DeleteAccountActivity.class); break;
                case "Currency":
                    intent = new Intent(context, CurrencySettingsActivity.class); break;
                case "Deliver to":
                    intent = new Intent(context, DeliverySettingsActivity.class); break;
                case "Feedback":
                    intent = new Intent(context, FeedbackActivity.class); break;
                case "Share":
                    intent = new Intent(context, ShareActivity.class); break;
            }
            if (intent != null) context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return settingsList.size();
    }

    public static class SettingsViewHolder extends RecyclerView.ViewHolder {
        TextView title, label;
        ImageView icon;

        public SettingsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.settingsTitle);
            label = itemView.findViewById(R.id.settingsLabel);
            icon = itemView.findViewById(R.id.settingsIcon);
        }
    }
}




