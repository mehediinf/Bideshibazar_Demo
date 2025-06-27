package com.mtach.bideshibazar.features;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class LanguageSettingsActivity extends AppCompatActivity {

    private RadioGroup languageRadioGroup;
    private RadioButton englishRadio;
    private Button saveBtn;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_settings);

        // Init views
        backIcon = findViewById(R.id.backIcon);
        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        englishRadio = findViewById(R.id.englishRadio);

        saveBtn = findViewById(R.id.saveBtn);

        // Back icon action
        backIcon.setOnClickListener(v -> finish());

        englishRadio.setChecked(true);

        // Save button click
        saveBtn.setOnClickListener(v -> {
            int selectedId = languageRadioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.englishRadio) {
                Toast.makeText(this, "Language set to English", Toast.LENGTH_SHORT).show();
                // savePreference("en");
            }
            finish(); // Close activity after saving
        });
    }

}
