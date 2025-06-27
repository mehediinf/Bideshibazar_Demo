package com.mtach.bideshibazar.account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.AccountSignActivity;
import com.mtach.bideshibazar.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView settingsRecyclerView;
    private SettingsAdapter adapter;
    private List<SettingsItem> settingsList;
    private Button signOutBtn;
    private ImageView backIcon;
    private TextView versionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Views
        settingsRecyclerView = findViewById(R.id.settingsRecyclerView);
        signOutBtn = findViewById(R.id.signOutBtn);
        backIcon = findViewById(R.id.backIcon);
        versionId = findViewById(R.id.versionId);

        // Back button action
        backIcon.setOnClickListener(v -> finish());

        // Optional: Set app version programmatically
        versionId.setText("Version " + getAppVersion());

        // Feature List
        settingsList = new ArrayList<>();
        settingsList.add(new SettingsItem("Language", "English"));
        settingsList.add(new SettingsItem("Notifications", ""));
        settingsList.add(new SettingsItem("Delete account", ""));
        settingsList.add(new SettingsItem("Currency", "EUR"));
        settingsList.add(new SettingsItem("Deliver to", "Austria"));
        settingsList.add(new SettingsItem("Feedback", ""));
        settingsList.add(new SettingsItem("Share", ""));



        // Setup RecyclerView
        adapter = new SettingsAdapter(this, settingsList);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        settingsRecyclerView.setAdapter(adapter);

        // Sign Out Button Action
        signOutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, AccountSignActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private String getAppVersion() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "1.0.1";
        }
    }
}






