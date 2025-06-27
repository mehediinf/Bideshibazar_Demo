package com.mtach.bideshibazar.features;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.mtach.bideshibazar.R;

public class NotificationSettingsActivity extends AppCompatActivity {

    private SwitchCompat switchPush;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        // Initialize views
        backIcon = findViewById(R.id.backIcon);
        switchPush = findViewById(R.id.switchPush);

        // Back action
        backIcon.setOnClickListener(v -> finish());

        // Switch Listeners
        switchPush.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(this, "Push Notifications " + (isChecked ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
            // Save to preferences if needed
        });



    }
}
