package com.mtach.bideshibazar.features;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackInput;
    private Button submitFeedbackBtn;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackInput = findViewById(R.id.feedbackInput);
        submitFeedbackBtn = findViewById(R.id.submitFeedbackBtn);
        backIcon = findViewById(R.id.backIcon);

        backIcon.setOnClickListener(v -> finish());

        submitFeedbackBtn.setOnClickListener(v -> {
            String feedback = feedbackInput.getText().toString().trim();

            if (TextUtils.isEmpty(feedback)) {
                Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Send feedback to server or save locally
                Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_LONG).show();
                feedbackInput.setText(""); // Clear input after submit
            }
        });
    }
}




