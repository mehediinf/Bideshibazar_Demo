package com.mtach.bideshibazar.store;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtach.bideshibazar.R;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    private RecyclerView productRecyclerView;
    private StoreProductAdapter productAdapter;

    private TextView forYou, bideshiBazar, asianOrientalHouse, mollahMarkt, polliBanglaSuperMarkt, goldenBanglaSuperMarket;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        // RecyclerView setup
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns
        productAdapter = new StoreProductAdapter(new ArrayList<>());
        productRecyclerView.setAdapter(productAdapter);

        // TextView bindings
        forYou = view.findViewById(R.id.forYouId);
        bideshiBazar = view.findViewById(R.id.bideshiBazarId);
        asianOrientalHouse = view.findViewById(R.id.asianOrientalHouse);
        mollahMarkt = view.findViewById(R.id.mollahMarkt);
        polliBanglaSuperMarkt = view.findViewById(R.id.polliBanglaSuperMarkt);
        goldenBanglaSuperMarket = view.findViewById(R.id.goldenBanglaSuperMarket);

        // Click Listeners
        forYou.setOnClickListener(v -> {
            setSelectedShop(forYou);
            loadProducts("foryou");
        });

        bideshiBazar.setOnClickListener(v -> {
            setSelectedShop(bideshiBazar);
            loadProducts("bideshi");
        });

        asianOrientalHouse.setOnClickListener(v -> {
            setSelectedShop(asianOrientalHouse);
            loadProducts("asian");
        });

        mollahMarkt.setOnClickListener(v -> {
            setSelectedShop(mollahMarkt);
            loadProducts("mollah");
        });

        polliBanglaSuperMarkt.setOnClickListener(v -> {
            setSelectedShop(polliBanglaSuperMarkt);
            loadProducts("polli");
        });

        goldenBanglaSuperMarket.setOnClickListener(v -> {
            setSelectedShop(goldenBanglaSuperMarket);
            loadProducts("golden");
        });

        // Default selection
        setSelectedShop(forYou);
        loadProducts("foryou");

        return view;
    }

    private void setSelectedShop(TextView selected) {
        // Reset all selections
        forYou.setSelected(false);
        bideshiBazar.setSelected(false);
        asianOrientalHouse.setSelected(false);
        mollahMarkt.setSelected(false);
        polliBanglaSuperMarkt.setSelected(false);
        goldenBanglaSuperMarket.setSelected(false);

        // Highlight the selected one
        selected.setSelected(true);
    }

    private void loadProducts(String shopId) {
        List<StoreProduct> productList = new ArrayList<>();

        switch (shopId) {
            case "foryou":
                productList.add(new StoreProduct("Popular Noodles", "25 added to cart", "120 sold ⭐ 4.9", "€0.99", "€1.20", "18% off", R.drawable.noodle));
                productList.add(new StoreProduct("Green Tea", "10 added to cart", "70 sold ⭐ 4.5", "€1.80", "€2.20", "15% off", R.drawable.tea));
                break;

            case "bideshi":
                productList.add(new StoreProduct("Tamarind Sauce", "18 added to cart", "45 sold ⭐ 4.4", "€1.70", "€2.00", "15% off", R.drawable.tamarind));
                break;

            case "asian":
                productList.add(new StoreProduct("Soy Sauce", "15 added to cart", "93 sold ⭐ 4.8", "€1.50", "€2.00", "25% off", R.drawable.soy_sauce));
                productList.add(new StoreProduct("Wasabi Paste", "8 added to cart", "40 sold ⭐ 4.5", "€3.00", "€4.00", "25% off", R.drawable.wasabi));
                break;

            case "mollah":
                productList.add(new StoreProduct("Deshi Rice", "40 added to cart", "200 sold ⭐ 4.7", "€10.99", "€14.00", "22% off", R.drawable.rice));
                productList.add(new StoreProduct("Mustard Oil", "18 added to cart", "88 sold ⭐ 4.6", "€4.20", "€5.20", "19% off", R.drawable.mustard_oil));
                break;

            case "polli":
                productList.add(new StoreProduct("Bangla Masala", "30 added to cart", "150 sold ⭐ 4.8", "€3.50", "€5.00", "30% off", R.drawable.masala));
                productList.add(new StoreProduct("Hilsha Fish", "12 added to cart", "40 sold ⭐ 4.5", "€12.00", "€15.00", "20% off", R.drawable.fish));
                break;

            case "golden":
                productList.add(new StoreProduct("Atta Flour", "21 added to cart", "67 sold ⭐ 4.6", "€5.50", "€6.50", "15% off", R.drawable.flour));
                productList.add(new StoreProduct("Spicy Chips", "11 added to cart", "98 sold ⭐ 4.7", "€1.10", "€1.50", "27% off", R.drawable.chips));
                break;
        }

        productAdapter.updateData(productList);
    }
}
