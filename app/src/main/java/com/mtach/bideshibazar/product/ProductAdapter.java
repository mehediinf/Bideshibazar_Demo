package com.mtach.bideshibazar.product;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productName.setText(product.getName());
        holder.addedToCart.setText(product.getAddedToCart() + " added to cart");
        holder.soldAndRating.setText(product.getSold() + " sold ⭐ " + product.getRating());
        holder.currentPrice.setText("BDT " + product.getCurrentPrice());
        holder.oldPrice.setText("BDT " + product.getOldPrice());
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discount.setText(product.getDiscount() + "% OFF");
        holder.productImage.setImageResource(product.getImageResId());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // 🔄 Clear current product list
    public void clearProducts() {
        productList.clear();
        notifyDataSetChanged();
    }

    // ➕ Add new products
    public void addProducts(List<Product> newProducts) {
        int startPos = productList.size();
        productList.addAll(newProducts);
        notifyItemRangeInserted(startPos, newProducts.size());
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
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
