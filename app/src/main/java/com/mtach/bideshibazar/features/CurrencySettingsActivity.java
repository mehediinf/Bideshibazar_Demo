package com.mtach.bideshibazar.features;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class CurrencySettingsActivity extends AppCompatActivity {

    private ImageView backIcon;
    private RadioGroup currencyGroup;
    private Button saveCurrencyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_settings);

        // View Init
        backIcon = findViewById(R.id.backIcon);
        currencyGroup = findViewById(R.id.currencyGroup);
        saveCurrencyBtn = findViewById(R.id.saveCurrencyBtn);

        // Back
        backIcon.setOnClickListener(v -> finish());

        // Save button
        saveCurrencyBtn.setOnClickListener(v -> {
            int selectedId = currencyGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadio = findViewById(selectedId);
                String selectedCurrency = selectedRadio.getText().toString();
                Toast.makeText(this, "Selected: " + selectedCurrency, Toast.LENGTH_SHORT).show();

                // TODO: Save to SharedPreferences or send to server
            } else {
                Toast.makeText(this, "Please select a currency", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


