package com.mtach.bideshibazar.store;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.product.ProductDetailsActivity;
import com.mtach.bideshibazar.R;

import java.util.List;

public class StoreProductAdapter extends RecyclerView.Adapter<StoreProductAdapter.ProductViewHolder> {

    private List<StoreProduct> productList;

    public StoreProductAdapter(List<StoreProduct> productList) {
        this.productList = productList;
    }

    public void updateData(List<StoreProduct> newList) {
        productList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new StoreProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreProductAdapter.ProductViewHolder holder, int position) {
        StoreProduct product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.addedToCart.setText(product.getAddedToCart());
        holder.soldAndRating.setText(product.getSoldAndRating());
        holder.currentPrice.setText(product.getCurrentPrice());
        holder.oldPrice.setText(product.getOldPrice());
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discount.setText(product.getDiscount());
        holder.productImage.setImageResource(product.getImageResId());

        // 👉 Handle click to open ProductDetailsActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("price", parsePrice(product.getCurrentPrice()));
            intent.putExtra("description", product.getSoldAndRating()); // Optional: use a different field if needed
            intent.putExtra("imageResId", product.getImageResId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, addedToCart, soldAndRating, currentPrice, oldPrice, discount;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            addedToCart = itemView.findViewById(R.id.addedToCart);
            soldAndRating = itemView.findViewById(R.id.soldAndRating);
            currentPrice = itemView.findViewById(R.id.currentPrice);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            discount = itemView.findViewById(R.id.discount);
        }
    }

    // 🔢 Helper to parse price string like "€1.50" to 1.50
    private static double parsePrice(String priceText) {
        try {
            return Double.parseDouble(priceText.replace("€", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
