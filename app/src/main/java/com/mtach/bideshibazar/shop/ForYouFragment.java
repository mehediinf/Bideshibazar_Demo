package com.mtach.bideshibazar.shop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import java.util.ArrayList;
import java.util.List;

public class ForYouFragment extends Fragment {

    private static final String ARG_CATEGORY = "category";
    private int currentPage = 1;
    private boolean isLoading = false;

    private ProductAdapter adapter;
    private final List<Product> productList = new ArrayList<>();

    public static CategoryFragment newInstance(String category) {
        Bundle b = new Bundle(); b.putString(ARG_CATEGORY, category);
        CategoryFragment f = new CategoryFragment();
        f.setArguments(b);
        return f;
    }


    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        /*  ⚠️  এখানে fragment_products.xml ইনফ্লেট করতে হবে item_product নয়  */
        View root = inf.inflate(R.layout.fragment_products, container, false);

        String category = getArguments() != null ? getArguments().getString(ARG_CATEGORY) : "general";

        /*  ⚠️  RecyclerView আইডি rvProducts  */
        RecyclerView rv = root.findViewById(R.id.rvProducts);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setHasFixedSize(true);

        adapter = new ProductAdapter(productList);
        rv.setAdapter(adapter);

        // প্রথম পেজ
        loadPage(category, currentPage);

        // ইনফিনিট স্ক্রল
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(@NonNull RecyclerView r, int dx, int dy) {
                if (dy <= 0) return;
                GridLayoutManager lm = (GridLayoutManager) r.getLayoutManager();
                if (lm == null) return;

                int visible  = lm.getChildCount();
                int total    = lm.getItemCount();
                int firstPos = lm.findFirstVisibleItemPosition();

                if (!isLoading && (visible + firstPos) >= total - 2) {
                    isLoading = true;
                    loadPage(category, ++currentPage);
                }
            }
        });

        return root;
    }

    /* ডামি ডাটা লোড (পেজভিত্তিক) */
    private void loadPage(String category, int page) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            List<Product> newData = Product.generateDummyData(category, page);
            adapter.addProducts(newData);
            isLoading = false;
        }, 400);
    }

}
