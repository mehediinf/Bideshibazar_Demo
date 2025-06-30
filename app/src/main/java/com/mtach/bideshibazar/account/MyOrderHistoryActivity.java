package com.mtach.bideshibazar.account;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrderHistoryActivity extends AppCompatActivity {

    private TextView tvTotalOrder, tvDelivered, tvProcessing, tvApproved, tvCancelled, tvReturned;
    private RecyclerView orderRecyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderModel> orderList;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_history);


        backIcon = findViewById(R.id.backIcon);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        initViews();
        setupRecyclerView();
        loadOrders();
    }

    private void initViews() {
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        tvDelivered = findViewById(R.id.tvDelivered);
        tvProcessing = findViewById(R.id.tvProcessing);
        tvApproved = findViewById(R.id.tvApproved);
        tvCancelled = findViewById(R.id.tvCancelled);
        tvReturned = findViewById(R.id.tvReturned);

        orderRecyclerView = findViewById(R.id.orderRecyclerView);
    }

    private void setupRecyclerView() {
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderList);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderRecyclerView.setAdapter(orderAdapter);
    }

    private void loadOrders() {
        orderList.add(new OrderModel("#1", "ORD1234", "Shop A", "$40", "$5", "$45", "Delivered", "https://bideshibazar.com/", "View"));
        orderList.add(new OrderModel("#2", "ORD1235", "Shop B", "$100", "$10", "$110", "Processing", "https://bideshibazar.com/", "View"));
        orderList.add(new OrderModel("#3", "ORD1236", "Shop C", "$80", "$8", "$88", "Cancelled", "", "View"));

        tvTotalOrder.setText("3");
        tvDelivered.setText("1");
        tvProcessing.setText("1");
        tvCancelled.setText("1");
        tvApproved.setText("0");
        tvReturned.setText("0");

        orderAdapter.notifyDataSetChanged();
    }
}
