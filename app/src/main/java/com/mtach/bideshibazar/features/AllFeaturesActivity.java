package com.mtach.bideshibazar.features;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;

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

        FeatureAdapter adapter = new FeatureAdapter(this, featureList);
        featuresRecyclerView.setAdapter(adapter);

        findViewById(R.id.backIcon).setOnClickListener(v -> onBackPressed());
    }
}
