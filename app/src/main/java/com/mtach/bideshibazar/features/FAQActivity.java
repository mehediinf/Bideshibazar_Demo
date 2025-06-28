package com.mtach.bideshibazar.features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    ExpandableListView faqListView;
    FAQAdapter faqAdapter;
    List<String> questionList;
    TextView furtherQuestions;
    HashMap<String, String> answerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        faqListView = findViewById(R.id.faqListView);
        furtherQuestions = findViewById(R.id.furtherQuestionsId);

        furtherQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FAQActivity.this,ComplainActivity.class));

            }
        });

        findViewById(R.id.backIcon).setOnClickListener(v -> onBackPressed());

        loadData();

        faqAdapter = new FAQAdapter(this, questionList, answerMap);
        faqListView.setAdapter(faqAdapter);


        //this to allow only one group to be expanded at a time
        faqListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    faqListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });



    }


    private void loadData() {
        questionList = new ArrayList<>();
        answerMap = new HashMap<>();

        questionList.add("How much do deliveries cost?");
        answerMap.put("How much do deliveries cost?", "Enjoy Free Delivery on All Orders.\n");

        questionList.add("What are your delivery hours?");
        answerMap.put("What are your delivery hours?", "Deliveries take 30 minutes to 2 hours, depending on the order and location.\n");

        questionList.add("What is your policy on refunds?");
        answerMap.put("What is your policy on refunds?", "Once the shop approves your order, there is no refund unless the products are defective.\n" +
                "\n" +
                "If the product is defective, customers must return it to the shop, and Bideshi Bazar will verify the issue.\n" +
                "\n" +
                "If the claim is valid, a full refund will be issued. If not, no refund will be provided.\n");

        questionList.add("What about the prices?");
        answerMap.put("What about the prices?", "Our prices match the local market, and we always strive to offer the best price to our customers.\n");

        questionList.add("Do you serve my area?");
        answerMap.put("Do you serve my area?", "We serve all of Vienna, with a maximum delivery distance of 6 km from any shop.\n");

        questionList.add("Is cash on delivery available?");
        answerMap.put("Is cash on delivery available?", "Yes, cash on delivery is possible if the shop approves it. Otherwise, online payment is required.\n");

        questionList.add("Can I schedule a delivery for a specific time?");
        answerMap.put("Can I schedule a delivery for a specific time?", "Yes, you can select a preferred delivery time slot when placing your order.\n");

        questionList.add("What happens if no one is available to receive the delivery?");
        answerMap.put("What happens if no one is available to receive the delivery?", "If no one is available, the delivery person will contact you. If the delivery fails, you can reschedule, but additional charges may apply.\n");

        questionList.add("Do you deliver fresh and frozen items?");
        answerMap.put("Do you deliver fresh and frozen items?", "Yes, we deliver fresh and frozen items, ensuring they are properly packaged to maintain quality during transit.\n");

        questionList.add("Are there any additional charges for COD?");
        answerMap.put("Are there any additional charges for COD?", "No additional charges for cash on delivery unless specified by the shop.\n");
    }
}








