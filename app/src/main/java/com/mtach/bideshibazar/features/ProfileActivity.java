package com.mtach.bideshibazar.features;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.mtach.bideshibazar.R;

public class ProfileActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etMobile;
    private Button btnUpdateProfile;
    private ImageView backIcon, qrId, cameraIcon, profileImageView;
    private TextView tvInitial;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        setupListeners();
        loadProfileData();
    }

    private void initViews() {
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        btnUpdateProfile = findViewById(R.id.signOutBtn);
        backIcon = findViewById(R.id.backIcon);
        qrId = findViewById(R.id.qrId);
        cameraIcon = findViewById(R.id.cameraIcon);
        profileImageView = findViewById(R.id.profileImageView);
        tvInitial = findViewById(R.id.userHighlightName); // Used to derive initial
    }

    private void setupListeners() {
        backIcon.setOnClickListener(v -> onBackPressed());

        cameraIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        qrId.setOnClickListener(v -> showQRCode());

        btnUpdateProfile.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || mobile.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
        });
    }

    private void showQRCode() {
        try {
            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String qrData = "Name: " + name + "\nEmail: " + email + "\nMobile: " + mobile;

            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400);

            ImageView qrImage = new ImageView(this);
            qrImage.setImageBitmap(bitmap);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Your QR Code")
                    .setView(qrImage)
                    .setPositiveButton("Close", (dialog, which) -> dialog.dismiss())
                    .show();

        } catch (Exception e) {
            Toast.makeText(this, "Failed to generate QR", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void loadProfileData() {
        String fullName = "Md. Mehedi Hasan";
        String email = "me***@gmail.com";
        String mobile = "01733888950";

        etFullName.setText(fullName);
        etEmail.setText(email);
        etMobile.setText(mobile);

        if (!fullName.isEmpty()) {
            TextView initialText = findViewById(R.id.userHighlightName);
            initialText.setText(fullName);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }
}


