package com.mtach.bideshibazar.features;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class ComplainActivity extends AppCompatActivity {

    EditText orderIdInput, additionalDetails;
    RadioGroup orderReceivedGroup, deliveryOnTimeGroup, issuesGroup, correctItemsGroup, goodConditionGroup, feedbackGroup;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        // Initialize views
        orderIdInput = findViewById(R.id.orderIdInput);
        additionalDetails = findViewById(R.id.additionalDetails);

        orderReceivedGroup = findViewById(R.id.orderReceivedGroup);
        deliveryOnTimeGroup = findViewById(R.id.deliveryOnTimeGroup);
        issuesGroup = findViewById(R.id.issuesGroup);
        correctItemsGroup = findViewById(R.id.correctItemsGroup);
        goodConditionGroup = findViewById(R.id.goodConditionGroup);
        feedbackGroup = findViewById(R.id.feedbackGroup);

        additionalDetails.setMovementMethod(new ScrollingMovementMethod());
        additionalDetails.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if (event.getAction() == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        });

        // Back button
        ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> finish());

        // Submit button
        findViewById(R.id.submitComplaint).setOnClickListener(v -> {
            if (isFormValid()) {
                Toast.makeText(this, "Complaint submitted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Please fill in the fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isFormValid() {
        return ( !orderIdInput.getText().toString().trim().isEmpty()) &&
                (!additionalDetails.getText().toString().trim().isEmpty()
                || orderReceivedGroup.getCheckedRadioButtonId() != -1
                || deliveryOnTimeGroup.getCheckedRadioButtonId() != -1
                || issuesGroup.getCheckedRadioButtonId() != -1
                || correctItemsGroup.getCheckedRadioButtonId() != -1
                || goodConditionGroup.getCheckedRadioButtonId() != -1
                || feedbackGroup.getCheckedRadioButtonId() != -1
            );
    }
}
