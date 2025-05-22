package com.mtach.bideshibazar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.mtach.bideshibazar.databinding.FragmentHomeBinding;
import com.mtach.bideshibazar.product.Product;
import com.mtach.bideshibazar.product.ProductAdapter;
import com.mtach.bideshibazar.product.SubcategoryProductActivity;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private HorizontalScrollView subCategoryRecyclerView;
    private HorizontalScrollView scrollFashion;
    private HorizontalScrollView scrollTicket;

    private Button groceriesBtn, fashionBtn, ticketBtn;

    // Optional: For managing subcategory selection
    private final List<TextView> grocerySubcategories = new ArrayList<>();
    private final List<TextView> fashionSubcategories = new ArrayList<>();
    private final List<TextView> ticketSubcategories = new ArrayList<>();

    private RecyclerView productRecyclerView,popularProductRecyclerView;

    private ProductAdapter productAdapter;

    private String currentCategory = "groceries"; // 🔧 Fix: initialized to avoid error

    private NestedScrollView nestedScrollView;

    private LinearLayout nextPageArrow;
    private TextView seeMoreText;
    private String selectedSubcategory = "";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Init buttons
        groceriesBtn = view.findViewById(R.id.groceriesBtn);
        fashionBtn = view.findViewById(R.id.fashionBtn);
        ticketBtn = view.findViewById(R.id.ticketBtn);

        // Init scroll containers
        subCategoryRecyclerView = view.findViewById(R.id.subCategoryRecyclerView);
        scrollFashion = view.findViewById(R.id.scrollFashion);
        scrollTicket = view.findViewById(R.id.scrollTicket);


        productRecyclerView = view.findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productAdapter = new ProductAdapter(new ArrayList<>());
        productRecyclerView.setAdapter(productAdapter);


        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        nextPageArrow = view.findViewById(R.id.nextPageArrow);
        seeMoreText = view.findViewById(R.id.seeMoreText);


        // ✅ New: Initialize popular product RecyclerView
        popularProductRecyclerView = view.findViewById(R.id.popularProduct);
        popularProductRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 columns

        /// Load popular product list
        List<Product> popularProducts = Product.generateDummyData("popular", 1);
        ProductAdapter popularAdapter = new ProductAdapter(popularProducts);
        popularProductRecyclerView.setAdapter(popularAdapter);
        popularProductRecyclerView.setVisibility(View.VISIBLE);


        WebView youtubeWebView = view.findViewById(R.id.youtubeWebView);
        setupYoutubeWebView(youtubeWebView);



        ViewPager2 trendingViewPager = view.findViewById(R.id.trendingViewPager);
        WormDotsIndicator dotsIndicator = view.findViewById(R.id.dots_indicator);

// ইমেজ লিস্ট
        List<Integer> imageList = Arrays.asList(
                R.drawable.slide1,
                R.drawable.slide2,
                R.drawable.slide3
        );

        ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), imageList);
        trendingViewPager.setAdapter(adapter);
        dotsIndicator.setViewPager2(trendingViewPager);


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int next = (trendingViewPager.getCurrentItem() + 1) % imageList.size();
                trendingViewPager.setCurrentItem(next, true);
                handler.postDelayed(this, 3000); // every 3 sec
            }
        };
        handler.postDelayed(runnable, 3000);







        // Subcategory click setup
        initSubCategoryClickListeners(view);

        // Default: Grocery selected
        setCategorySelected(groceriesBtn, fashionBtn, ticketBtn);
        showCategory("grocery");

        // Default: "All" in grocery subcategory selected
        selectDefaultSubcategory(grocerySubcategories);

        // Set listeners
        groceriesBtn.setOnClickListener(v -> {
            loadCategory("groceries");
            setCategorySelected(groceriesBtn, fashionBtn, ticketBtn);
            showCategory("grocery");
            selectDefaultSubcategory(grocerySubcategories);
            scrollToTop(); // 👈 Scroll to top
        });

        fashionBtn.setOnClickListener(v -> {
            loadCategory("fashion");
            setCategorySelected(fashionBtn, groceriesBtn, ticketBtn);
            showCategory("fashion");
            selectDefaultSubcategory(fashionSubcategories);
            scrollToTop(); // 👈 Scroll to top
        });

        ticketBtn.setOnClickListener(v -> {
            loadCategory("tickets");
            setCategorySelected(ticketBtn, groceriesBtn, fashionBtn);
            showCategory("ticket");
            selectDefaultSubcategory(ticketSubcategories);
            scrollToTop(); // 👈 Scroll to top
        });




        nextPageArrow.setOnClickListener(v -> {
            if (!selectedSubcategory.isEmpty()) {
                Intent intent = new Intent(getActivity(), SubcategoryProductActivity.class);
                intent.putExtra("subcategory", selectedSubcategory);
                startActivity(intent);
            }
        });




        return view;
    }

    private void scrollToTop() {
        if (nestedScrollView != null) {
            nestedScrollView.post(() -> nestedScrollView.fullScroll(View.FOCUS_UP));
        }
    }



    /**
     * Shows and hides subcategory views based on selected category
     */
    private void showCategory(String category) {
        subCategoryRecyclerView.setVisibility(View.GONE);
        scrollFashion.setVisibility(View.GONE);
        scrollTicket.setVisibility(View.GONE);

        switch (category) {
            case "grocery":
                subCategoryRecyclerView.setVisibility(View.VISIBLE);
                break;
            case "fashion":
                scrollFashion.setVisibility(View.VISIBLE);
                break;
            case "ticket":
                scrollTicket.setVisibility(View.VISIBLE);
                break;
        }

        // You can also update the product list here
        // loadProductsForCategory(category);
    }

    /**
     * Highlights the selected category button
     */
    private void setCategorySelected(Button selectedButton, Button... otherButtons) {
        selectedButton.setSelected(true);
        for (Button btn : otherButtons) {
            btn.setSelected(false);
        }
    }



    /**
     * Highlights the selected subcategory TextView
     */
//    private void setSubCategorySelected(TextView selectedTextView, List<TextView> allSubcategories) {
//        for (TextView tv : allSubcategories) {
//            tv.setSelected(tv == selectedTextView);
//        }
//    }


//    private void setSubCategorySelected(TextView selectedTextView, List<TextView> allSubcategories) {
//        for (TextView tv : allSubcategories) {
//            tv.setSelected(tv == selectedTextView);
//        }
//
//        // Load dummy products
//        String subcategory = selectedTextView.getText().toString();
//        loadProductsForSubcategory(subcategory);
//    }

    private void setSubCategorySelected(TextView selectedTextView, List<TextView> allSubcategories) {
        for (TextView tv : allSubcategories) {
            tv.setSelected(tv == selectedTextView);
        }

        // Save selected subcategory name
        selectedSubcategory = selectedTextView.getText().toString();

        // Update See more text
        seeMoreText.setText("See more from \"" + selectedSubcategory + "\"");

        // Load dummy products
        loadProductsForSubcategory(selectedSubcategory);
    }



    /**
     * Call this method if you're using hardcoded subcategory TextViews in your layout.
     * Example assumes LinearLayout containing TextViews for subcategories.
     */
    private void initSubCategoryClickListeners(View rootView) {
        LinearLayout groceryLayout = rootView.findViewById(R.id.grocerySubCategoryLayout);
        LinearLayout fashionLayout = rootView.findViewById(R.id.fashionSubCategoryLayout);
        LinearLayout ticketLayout = rootView.findViewById(R.id.ticketSubCategoryLayout);

        // Initialize grocery subcategories
        if (groceryLayout != null) {
            for (int i = 0; i < groceryLayout.getChildCount(); i++) {
                View child = groceryLayout.getChildAt(i);
                if (child instanceof TextView) {
                    TextView tv = (TextView) child;
                    grocerySubcategories.add(tv);
                    tv.setOnClickListener(v -> setSubCategorySelected(tv, grocerySubcategories));
                }
            }
        }

        // Initialize fashion subcategories
        if (fashionLayout != null) {
            for (int i = 0; i < fashionLayout.getChildCount(); i++) {
                View child = fashionLayout.getChildAt(i);
                if (child instanceof TextView) {
                    TextView tv = (TextView) child;
                    fashionSubcategories.add(tv);
                    tv.setOnClickListener(v -> setSubCategorySelected(tv, fashionSubcategories));
                }
            }
        }

        // Initialize ticket subcategories
        if (ticketLayout != null) {
            for (int i = 0; i < ticketLayout.getChildCount(); i++) {
                View child = ticketLayout.getChildAt(i);
                if (child instanceof TextView) {
                    TextView tv = (TextView) child;
                    ticketSubcategories.add(tv);
                    tv.setOnClickListener(v -> setSubCategorySelected(tv, ticketSubcategories));
                }
            }
        }
    }

    private void selectDefaultSubcategory(List<TextView> subcategoryList) {
        for (TextView tv : subcategoryList) {
            if (tv.getText().toString().equalsIgnoreCase("All")) {
                setSubCategorySelected(tv, subcategoryList);
                break;
            }
        }
    }



    private void loadProductsForSubcategory(String subcategory) {
        int page = 1;

        List<Product> dummyList = new ArrayList<>(Product.generateDummyData(subcategory, page));

        productAdapter.clearProducts();
        productAdapter.addProducts(dummyList);
        productRecyclerView.scrollToPosition(0);
    }


    private void loadCategory(String category) {
        currentCategory = category;
        int page = 1;
        productAdapter.clearProducts();
        productAdapter.addProducts(Product.generateDummyData(currentCategory, page));
        productRecyclerView.scrollToPosition(0);
    }


    private void setupYoutubeWebView(WebView webView) {
        String videoId = "0k32gxIdlI4";  // তোমার ভিডিও আইডি
        String videoEmbedUrl = "<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/" + videoId + "\" " +
                "frameborder=\"0\" allowfullscreen></iframe>";

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String html = "<html><body style='margin:0;padding:0;'>" + videoEmbedUrl + "</body></html>";
        webView.loadData(html, "text/html", "utf-8");
    }










}
