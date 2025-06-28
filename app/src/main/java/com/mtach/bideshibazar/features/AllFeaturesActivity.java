package com.mtach.bideshibazar.features;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;
import com.mtach.bideshibazar.account.MyOrderHistoryActivity;
import com.mtach.bideshibazar.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class AllFeaturesActivity extends AppCompatActivity {

    RecyclerView featuresRecyclerView;
    List<FeatureItem> featureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_features);

        featuresRecyclerView = findViewById(R.id.featuresRecyclerView);
        featuresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add feature list
        featureList = new ArrayList<>();
        featureList.add(new FeatureItem("Profile"));
        featureList.add(new FeatureItem("Tax information"));
        featureList.add(new FeatureItem("Add address"));
        featureList.add(new FeatureItem("Settings"));
        featureList.add(new FeatureItem("Browsing history"));
        featureList.add(new FeatureItem("Orders"));
        featureList.add(new FeatureItem("Payment"));
        featureList.add(new FeatureItem("Bideshibazar club"));
        featureList.add(new FeatureItem("Seller Apps"));
        featureList.add(new FeatureItem("Complain"));
        featureList.add(new FeatureItem("Privacy Policy"));
        featureList.add(new FeatureItem("Return Policy"));
        featureList.add(new FeatureItem("FQA"));

        FeatureAdapter adapter = new FeatureAdapter(this, featureList, item -> openFeaturePage(item.getTitle()));
        featuresRecyclerView.setAdapter(adapter);

        findViewById(R.id.backIcon).setOnClickListener(v -> onBackPressed());
    }

    private void openFeaturePage(String title) {
        Intent intent = null;
        switch (title) {
            case "Profile":
                intent = new Intent(this, ProfileActivity.class);
                break;
            case "Tax information":
                intent = new Intent(this, TaxInformationActivity.class);
                break;
            case "Add address":
                intent = new Intent(this, AddAddressActivity.class);
                break;
            case "Settings":
                intent = new Intent(this, com.mtach.bideshibazar.account.SettingsActivity.class);
                break;
            case "Browsing history":
                intent = new Intent(this, BrowsingHistoryActivity.class);
                break;
            case "Orders":
                intent = new Intent(this, MyOrderHistoryActivity.class);
                break;
            case "Payment":
                intent = new Intent(this, PaymentActivity.class);
                break;
            case "Bideshibazar club":
                intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", "https://bideshibazar.com/club");
                startActivity(intent);
                break;
            case "Seller Apps":
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.mtech.bideshibazarseller&pcampaignid=web_share"));
                startActivity(intent);
                break;

            case "Complain":
                intent = new Intent(this, ComplainActivity.class);
                break;
            case "Privacy Policy":
                intent = new Intent(this, PrivacyPolicyActivity.class);
                break;
            case "Return Policy":
                intent = new Intent(this, ReturnPolicyActivity.class);
                break;
            case "FQA":
                intent = new Intent(this, FAQActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}









