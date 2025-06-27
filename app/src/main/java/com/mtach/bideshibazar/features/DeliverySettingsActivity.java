package com.mtach.bideshibazar.features;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class DeliverySettingsActivity extends AppCompatActivity {

    private ImageView backIcon;
    private RadioGroup countryGroup;
    private Button saveCountryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_settings);

        // View initialization
        backIcon = findViewById(R.id.backIcon);
        countryGroup = findViewById(R.id.countryGroup);
        saveCountryBtn = findViewById(R.id.saveCountryBtn);

        // Back
        backIcon.setOnClickListener(v -> finish());

        // Save button
        saveCountryBtn.setOnClickListener(v -> {
            int selectedId = countryGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                String selectedCountry = selected.getText().toString();
                Toast.makeText(this, "Deliver to: " + selectedCountry, Toast.LENGTH_SHORT).show();

                // TODO: Save to SharedPreferences or send to server
            } else {
                Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
