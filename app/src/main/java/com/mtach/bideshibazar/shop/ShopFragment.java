package com.mtach.bideshibazar.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mtach.bideshibazar.R;


public class ShopFragment extends Fragment {

    private ListView listView;
    private final String[] titles = {
            "For you", "Ready to Eat", "Fruit and Vegetables", "Drinks",
            "Rice & Noodles", "Flours & Cereals", "Beans, Nuts & Pulses",
            "Coconut & Milk", "Oils & Butter", "Sweets & Snacks",
            "Herbs & Spices", "Fish, Meat & Soya", "Sauces",
            "Cooking Pastes & Kits", "Dal", "Personal Care",
            "Electronic Accessories", "Alcohol", "Beauty Products"
    };

    private final Fragment[] fragments = {
            new ForYouFragment(),
            CategoryFragment.newInstance("Ready to Eat"),
            CategoryFragment.newInstance("Fruit and Vegetables"),
            CategoryFragment.newInstance("Drinks"),
            CategoryFragment.newInstance("Rice & Noodles"),
            CategoryFragment.newInstance("Flours & Cereals"),
            CategoryFragment.newInstance("Beans, Nuts & Pulses"),
            CategoryFragment.newInstance("Coconut & Milk"),
            CategoryFragment.newInstance("Oils & Butter"),
            CategoryFragment.newInstance("Sweets & Snacks"),
            CategoryFragment.newInstance("Herbs & Spices"),
            CategoryFragment.newInstance("Fish, Meat & Soya"),
            CategoryFragment.newInstance("Sauces"),
            CategoryFragment.newInstance("Cooking Pastes & Kits"),
            CategoryFragment.newInstance("Dal"),
            CategoryFragment.newInstance("Personal Care"),
            CategoryFragment.newInstance("Electronic Accessories"),
            CategoryFragment.newInstance("Alcohol"),
            CategoryFragment.newInstance("Beauty Products")
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        listView = root.findViewById(R.id.categoryListView);

        /* অ্যাডাপটার সেট‑আপ */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.item_category,
                R.id.tvCategory,
                titles
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            listView.setItemChecked(position, true);          // সিলেক্টেড স্টেট সেট
            replaceChildFragment(fragments[position]);
        });


        /* ডিফল্ট নির্বাচন = প্রথম আইটেম */
        listView.post(() -> {
            listView.setItemChecked(0, true);
            replaceChildFragment(fragments[0]);
        });

        return root;
    }

    private void replaceChildFragment(Fragment fragment) {
        if (!isAdded()) return;
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
