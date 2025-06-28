package com.mtach.bideshibazar.features;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class AddAddressActivity extends AppCompatActivity {

    EditText etFullAddress, etRoad, etHouse, etRoom, etPostCode, etCity;
    Button btnAddAddress;
    ImageView backIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

// Bind views
        backIcon = findViewById(R.id.backIcon);
        etFullAddress = findViewById(R.id.etFullAddress);
        etRoad = findViewById(R.id.etRoad);
        etHouse = findViewById(R.id.etHouse);
        etRoom = findViewById(R.id.etRoom);
        etPostCode = findViewById(R.id.etPostCode);
        etCity = findViewById(R.id.etCity);
        btnAddAddress = findViewById(R.id.btnAddAddress);

        // Back button
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back
            }
        });

        // Add Address button logic
        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullAddress = etFullAddress.getText().toString().trim();
                String road = etRoad.getText().toString().trim();
                String house = etHouse.getText().toString().trim();
                String room = etRoom.getText().toString().trim();
                String postCode = etPostCode.getText().toString().trim();
                String city = etCity.getText().toString().trim();

                // Validation
                if (fullAddress.isEmpty()) {
                    etFullAddress.setError("Please enter full address");
                    return;
                }
                if (road.isEmpty()) {
                    etRoad.setError("Enter road");
                    return;
                }
                if (house.isEmpty()) {
                    etHouse.setError("Enter house");
                    return;
                }
                if (room.isEmpty()) {
                    etRoom.setError("Enter room");
                    return;
                }
                if (postCode.isEmpty()) {
                    etPostCode.setError("Enter post code");
                    return;
                }
                if (city.isEmpty()) {
                    etCity.setError("Enter city");
                    return;
                }

                // Submit or save
                Toast.makeText(AddAddressActivity.this, "Address added successfully", Toast.LENGTH_SHORT).show();

                // You can now save to DB or call API here
            }
        });
    }
}