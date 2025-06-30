package com.mtach.bideshibazar.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.R;
import com.mtach.bideshibazar.product.Product;
import com.mtach.bideshibazar.product.ProductAdapter;

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productRecyclerView = view.findViewById(R.id.productRecyclerView);

        // ✅ Use GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        productRecyclerView.setLayoutManager(gridLayoutManager);

        // Dummy Data
        List<Product> dummyProducts = Product.generateDummyGroceries(); // or .generateDummyFashion()
        productAdapter = new ProductAdapter(dummyProducts);
        productRecyclerView.setAdapter(productAdapter);
    }
}
