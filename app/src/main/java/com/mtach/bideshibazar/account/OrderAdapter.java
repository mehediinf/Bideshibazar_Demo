package com.mtach.bideshibazar.account;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<OrderModel> orders;

    public OrderAdapter(List<OrderModel> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_row, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderModel order = orders.get(position);

        holder.sl.setText(order.sl);
        holder.orderNo.setText(order.orderNo);
        holder.shopName.setText(order.shopName);
        holder.subtotal.setText(order.subtotal);
        holder.deliveryCharge.setText(order.deliveryCharge);
        holder.total.setText(order.total);
        holder.status.setText(order.status);
        holder.woltTracking.setText(order.trackingUrl.isEmpty() ? "N/A" : "Track");
        holder.action.setText(order.action);

        holder.woltTracking.setOnClickListener(v -> {
            if (!order.trackingUrl.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(order.trackingUrl));
                v.getContext().startActivity(intent);
            } else {
                Toast.makeText(v.getContext(), "No tracking available", Toast.LENGTH_SHORT).show();
            }
        });

        holder.action.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Action clicked for Order: " + order.orderNo, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView sl, orderNo, shopName, subtotal, deliveryCharge, total, status, woltTracking, action;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            sl = itemView.findViewById(R.id.tvSl);
            orderNo = itemView.findViewById(R.id.tvOrderNo);
            shopName = itemView.findViewById(R.id.tvShopName);
            subtotal = itemView.findViewById(R.id.tvSubtotal);
            deliveryCharge = itemView.findViewById(R.id.tvDeliveryCharge);
            total = itemView.findViewById(R.id.tvTotal);
            status = itemView.findViewById(R.id.tvStatus);
            woltTracking = itemView.findViewById(R.id.tvWoltTracking);
            action = itemView.findViewById(R.id.tvAction);
        }
    }
}