package com.mtach.bideshibazar.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtach.bideshibazar.AccountSignActivity;
import com.mtach.bideshibazar.R;
import com.mtach.bideshibazar.store.StoreProduct;
import com.mtach.bideshibazar.store.StoreProductAdapter;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {
    private LinearLayout myOrderLayout,webLinkLayout;
    private RecyclerView productRecyclerView;
    private StoreProductAdapter productAdapter;
    private ImageView headsetIcon, settingsIcon;

    private Button singInBtn;
    private List<TextView> categoryButtons = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        singInBtn = view.findViewById(R.id.btnSignIn);
        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productAdapter = new StoreProductAdapter(new ArrayList<>());
        productRecyclerView.setAdapter(productAdapter);
        headsetIcon = view.findViewById(R.id.headsetId);
        settingsIcon = view.findViewById(R.id.settingsId);


        singInBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AccountSignActivity.class);
            startActivity(intent);
        });
        headsetIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SupportActivity.class);
            startActivity(intent);
        });
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });

        // My Order Section Click Listener
        myOrderLayout = view.findViewById(R.id.myOrder);
        myOrderLayout.setOnClickListener(v -> {
            // উদাহরণ: Order History Activity খুলবে
            Intent intent = new Intent(getActivity(), MyOrderHistoryActivity.class);
            startActivity(intent);
        });

        // Web Link Section Click Listener
        webLinkLayout = view.findViewById(R.id.webLink);
        webLinkLayout.setOnClickListener(v -> {
            String url = "https://www.bideshibazar.com";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });




        // Add all category buttons
        int[] buttonIds = {
                R.id.grocery_All,
                R.id.grocery_ReadyToEat,
                R.id.grocery_FruitVegetables,
                R.id.grocery_Drinks,
                R.id.grocery_RiceNoodles,
                R.id.grocery_FloursCereals,
                R.id.grocery_NutsPulses,
                R.id.grocery_CoconutMilk,
                R.id.grocery_OilsButter,
                R.id.grocery_SweetsSnacks,
                R.id.grocery_HerbsSpices,
                R.id.grocery_MeatSoya,
                R.id.grocery_Sauces,
                R.id.grocery_CookingPastesKits,
                R.id.grocery_Dal,
                R.id.grocery_PersonalCare,
                R.id.grocery_ElectronicAccessories,
                R.id.grocery_BeautyProducts
        };

        for (int id : buttonIds) {
            TextView btn = view.findViewById(id);
            categoryButtons.add(btn);
            btn.setOnClickListener(v -> {
                resetAllSelections();
                btn.setSelected(true);
                loadProducts(btn.getText().toString());
            });
        }

        // Load default
        if (!categoryButtons.isEmpty()) {
            categoryButtons.get(0).performClick();
        }

        return view;
    }

    private void resetAllSelections() {
        for (TextView tv : categoryButtons) {
            tv.setSelected(false);
        }
    }

    private void loadProducts(String categoryName) {
        List<StoreProduct> productList = new ArrayList<>();

        if (categoryName.contains("All")) {
            productList.add(new StoreProduct("Popular Noodles", "25 added to cart", "120 sold ⭐ 4.9", "€0.99", "€1.20", "18% off", R.drawable.noodle));
            productList.add(new StoreProduct("Green Tea", "10 added to cart", "70 sold ⭐ 4.5", "€1.80", "€2.20", "15% off", R.drawable.tea));
        } else if (categoryName.contains("Ready to Eat")) {
            productList.add(new StoreProduct("Instant Paratha", "12 added to cart", "60 sold ⭐ 4.3", "€2.50", "€3.00", "17% off", R.drawable.paratha));
        } else if (categoryName.contains("Fruit and Vegetables")) {
            productList.add(new StoreProduct("Fresh Green Chili", "8 added to cart", "30 sold ⭐ 4.6", "€1.20", "€1.50", "20% off", R.drawable.chili));
        } else if (categoryName.contains("Drinks")) {
            productList.add(new StoreProduct("Mango Juice", "9 added to cart", "65 sold ⭐ 4.6", "€1.00", "€1.30", "23% off", R.drawable.juice));
        } else if (categoryName.contains("Rice & Noodles")) {
            productList.add(new StoreProduct("Deshi Rice", "40 added to cart", "200 sold ⭐ 4.7", "€10.99", "€14.00", "22% off", R.drawable.rice));
            productList.add(new StoreProduct("Egg Noodles", "15 added to cart", "88 sold ⭐ 4.6", "€1.10", "€1.50", "26% off", R.drawable.noodle));
        } else if (categoryName.contains("Flours & Cereals")) {
            productList.add(new StoreProduct("Atta Flour", "21 added to cart", "67 sold ⭐ 4.6", "€5.50", "€6.50", "15% off", R.drawable.flour));
        } else if (categoryName.contains("Nuts & Pulses")) {
            productList.add(new StoreProduct("Moong Dal", "13 added to cart", "78 sold ⭐ 4.5", "€3.00", "€3.80", "21% off", R.drawable.dal));
        } else if (categoryName.contains("Coconut & Milk")) {
            productList.add(new StoreProduct("Coconut Milk Can", "6 added to cart", "25 sold ⭐ 4.2", "€1.80", "€2.20", "18% off", R.drawable.coconut_milk));
        } else if (categoryName.contains("Oils & Butter")) {
            productList.add(new StoreProduct("Mustard Oil", "18 added to cart", "88 sold ⭐ 4.6", "€4.20", "€5.20", "19% off", R.drawable.mustard_oil));
        } else if (categoryName.contains("Sweets & Snacks")) {
            productList.add(new StoreProduct("Spicy Chips", "11 added to cart", "98 sold ⭐ 4.7", "€1.10", "€1.50", "27% off", R.drawable.chips));
        } else if (categoryName.contains("Herbs & Spices")) {
            productList.add(new StoreProduct("Red Chili Powder", "13 added to cart", "58 sold ⭐ 4.8", "€2.00", "€2.50", "20% off", R.drawable.masala));
        } else if (categoryName.contains("Meat & Soya")) {
            productList.add(new StoreProduct("Hilsha Fish", "12 added to cart", "40 sold ⭐ 4.5", "€12.00", "€15.00", "20% off", R.drawable.fish));
        } else if (categoryName.contains("Sauces")) {
            productList.add(new StoreProduct("Soy Sauce", "15 added to cart", "93 sold ⭐ 4.8", "€1.50", "€2.00", "25% off", R.drawable.soy_sauce));
        } else if (categoryName.contains("Cooking Pastes & Kits")) {
            productList.add(new StoreProduct("Ginger Garlic Paste", "7 added to cart", "52 sold ⭐ 4.4", "€1.90", "€2.30", "17% off", R.drawable.paste));
        } else if (categoryName.contains("Dal")) {
            productList.add(new StoreProduct("Cholar Dal", "16 added to cart", "90 sold ⭐ 4.7", "€2.40", "€3.00", "20% off", R.drawable.dal));
        } else if (categoryName.contains("Personal Care")) {
            productList.add(new StoreProduct("Herbal Shampoo", "11 added to cart", "45 sold ⭐ 4.6", "€4.00", "€5.00", "20% off", R.drawable.shampoo));
        } else if (categoryName.contains("Electronic Accessories")) {
            productList.add(new StoreProduct("USB Charger", "18 added to cart", "110 sold ⭐ 4.5", "€6.00", "€7.50", "20% off", R.drawable.charger));
        } else if (categoryName.contains("Beauty Products")) {
            productList.add(new StoreProduct("Fairness Cream", "9 added to cart", "39 sold ⭐ 4.3", "€3.30", "€4.00", "18% off", R.drawable.beauty));
        }

        productAdapter.updateData(productList);
    }
}