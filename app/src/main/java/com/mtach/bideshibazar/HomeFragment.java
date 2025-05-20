package com.mtach.bideshibazar;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtach.bideshibazar.product.Product;
import com.mtach.bideshibazar.product.ProductAdapter;
import com.mtach.bideshibazar.shop.ShopFragment;

import java.util.List;

public class HomeFragment extends Fragment {

    private Button groceriesBtn, fashionBtn, ticketBtn;
    private RecyclerView productRecyclerView;
    private LinearLayout nextPageArrow;

    private List<Product> groceriesList;
    private int currentPage = 0;
    private static final int ITEMS_PER_PAGE = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        groceriesBtn = view.findViewById(R.id.groceriesBtn);
        fashionBtn = view.findViewById(R.id.fashionBtn);
        ticketBtn = view.findViewById(R.id.ticketBtn);
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        nextPageArrow = view.findViewById(R.id.nextPageArrow);

        // Set LayoutManager
        productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        // Load product list (dummy data)
        groceriesList = getGroceriesProducts();

        // Grocery Button Click
        groceriesBtn.setOnClickListener(v -> {
            currentPage = 0;
            loadCurrentPage();
            setActiveCategory(groceriesBtn);
        });

        // Fashion / Ticket Button - Add if needed later

        // Next Page Arrow Click
        nextPageArrow.setOnClickListener(v -> {


            //startActivity(new Intent(getActivity(), ShopFragment.class));

            int maxPages = (int) Math.ceil((double) groceriesList.size() / ITEMS_PER_PAGE);
            if (currentPage < maxPages - 1) {
                currentPage++;
                loadCurrentPage();
            }
        });

        // ✅ Default Load
        groceriesBtn.performClick(); // Trigger click to auto-load groceries & apply UI style

        return view;
    }

    // Optional method to highlight active category visually
    private void setActiveCategory(Button activeButton) {
        groceriesBtn.setAlpha(0.5f);
        fashionBtn.setAlpha(0.5f);
        ticketBtn.setAlpha(0.5f);
        activeButton.setAlpha(1.0f); // Highlight selected
    }


    private void loadCurrentPage() {
        List<Product> pageItems = getProductsByPage(groceriesList, currentPage);
        ProductAdapter adapter = new ProductAdapter(pageItems);
        productRecyclerView.setAdapter(adapter);
    }

    // Dummy data generator
    private List<Product> getGroceriesProducts() {
        return Product.generateDummyGroceries(); // Make sure this method exists in Product class
    }

    private List<Product> getProductsByPage(List<Product> fullList, int page) {
        int start = page * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, fullList.size());
        return fullList.subList(start, end);
    }
}