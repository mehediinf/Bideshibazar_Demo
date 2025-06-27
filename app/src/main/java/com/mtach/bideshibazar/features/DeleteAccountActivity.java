package com.mtach.bideshibazar.features;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class DeleteAccountActivity extends AppCompatActivity {

    private ImageView backIcon;
    private Button deleteAccountBtn;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        backIcon = findViewById(R.id.backIcon);
        deleteAccountBtn = findViewById(R.id.deleteAccountBtn);
        passwordInput = findViewById(R.id.passwordInput);

        // Back icon action
        backIcon.setOnClickListener(v -> finish());

        deleteAccountBtn.setOnClickListener(v -> {
            String password = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(password)) {
                passwordInput.setError("Password required");
                passwordInput.requestFocus();
                return;
            }

            // Show confirmation dialog
            new AlertDialog.Builder(DeleteAccountActivity.this)
                    .setTitle("Confirm Deletion")
                    .setMessage("Are you sure you want to delete your account? This cannot be undone.")
                    .setPositiveButton("Yes, Delete", (dialog, which) -> {
                        // TODO: Call API to delete account
                        Toast.makeText(this, "Account deletion in process...", Toast.LENGTH_LONG).show();
                        finish(); // close activity
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}






