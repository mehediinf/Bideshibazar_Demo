package com.mtach.bideshibazar.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;

import java.util.List;

public class StoreProductAdapter extends RecyclerView.Adapter<com.mtach.bideshibazar.store.StoreProductAdapter.ProductViewHolder> {

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
    public com.mtach.bideshibazar.store.StoreProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new com.mtach.bideshibazar.store.StoreProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.mtach.bideshibazar.store.StoreProductAdapter.ProductViewHolder holder, int position) {
        StoreProduct product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.addedToCart.setText(product.getAddedToCart());
        holder.soldAndRating.setText(product.getSoldAndRating());
        holder.currentPrice.setText(product.getCurrentPrice());
        holder.oldPrice.setText(product.getOldPrice());
        holder.discount.setText(product.getDiscount());
        holder.productImage.setImageResource(product.getImageResId());
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
}

