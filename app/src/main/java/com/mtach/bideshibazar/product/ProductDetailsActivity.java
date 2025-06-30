package com.mtach.bideshibazar.product;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.mtach.bideshibazar.MainActivity;
import com.mtach.bideshibazar.R;
import com.mtach.bideshibazar.network.ProductDetailsApi;
import com.mtach.bideshibazar.network.RelatedProductApi;
import com.mtach.bideshibazar.network.RetrofitClient;
import com.mtach.bideshibazar.store.StoreInfo;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mtach.bideshibazar.store.StoreProduct;
import com.mtach.bideshibazar.store.StoreProductAdapter;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImage,backIcon,homeIcon;
    private TextView productTitle, productPrice, productDescription,seeMore;
    private TextView storeName, storeAddress, contact, hotline,txtQuantity;
    private Button btnPlus,btnMinus,btnAddToCart;


    private RecyclerView productRecyclerView;
    private StoreProductAdapter adapter;
    private List<StoreProduct> relatedProducts = new ArrayList<>();


    private int imageResId;
    private String imageUrl; // If loading from API later
    private boolean isExpanded = false;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        bindViews();
        loadProductFromIntent();
        loadStoreInfo();

        setupRecyclerView();
        loadRelatedProductsFromApi();



        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        homeIcon.setOnClickListener(v -> {
            // Go to HomeFragment via MainActivity or use finish if backstack works
            Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
            intent.putExtra("goToHome", true); // optional flag
            startActivity(intent);
            finish(); // optional, if you don't want to keep details page in backstack
        });



//        int productId = getIntent().getIntExtra("productId", -1);
//        if (productId != -1) {
//            loadProductDetailsFromApi(productId);
//        } else {
//            loadProductFromIntent();
//        }

        productImage.setOnClickListener(v -> showImageDialog(imageResId));



//Button ......................
        btnPlus.setOnClickListener(v -> {
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                txtQuantity.setText(String.valueOf(quantity));
            }
        });

        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(this, quantity + " item(s) added to cart", Toast.LENGTH_SHORT).show();
        });




// Sample related products (তুমি চাইলে API থেকেও আনতে পারো)
        List<StoreProduct> relatedProducts = new ArrayList<>();
        relatedProducts.add(new StoreProduct("Onion Chips", "12 added to cart", "231 sold ⭐ 4.5", "€ 2.99", "€ 4.50", "25% OFF", R.drawable.chips));
        relatedProducts.add(new StoreProduct("Chanachur Mix", "7 added to cart", "184 sold ⭐ 4.2", "€ 1.50", "€ 2.00", "20% OFF", R.drawable.chili));
        relatedProducts.add(new StoreProduct("Dry Cake", "5 added to cart", "150 sold ⭐ 4.8", "€ 3.20", "€ 4.00", "15% OFF", R.drawable.noodle));

        adapter = new StoreProductAdapter(relatedProducts);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productRecyclerView.setAdapter(adapter);


    }

    private void bindViews() {
        productImage = findViewById(R.id.productImage);
        productTitle = findViewById(R.id.productTitle);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);
        seeMore = findViewById(R.id.seeMore);

        storeName = findViewById(R.id.storeName);
        storeAddress = findViewById(R.id.storeAddress);
        contact = findViewById(R.id.contact);
        hotline = findViewById(R.id.hotline);
        txtQuantity = findViewById(R.id.txtQuantity);

        productRecyclerView = findViewById(R.id.productRecyclerView);

        backIcon = findViewById(R.id.backIcon);
        homeIcon = findViewById(R.id.homeIcon);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnAddToCart = findViewById(R.id.btnAddToCart);
    }

    private void loadProductFromIntent() {
        String name = getIntent().getStringExtra("name");
        double price = getIntent().getDoubleExtra("price", 0.0);
        String description = getIntent().getStringExtra("description");
        imageResId = getIntent().getIntExtra("imageResId", R.drawable.pr1);
        productImage.setImageResource(imageResId);

        productTitle.setText(name != null ? name : "No Title");
        productPrice.setText("€ " + price);

        if (description != null && !description.isEmpty()) {
            productDescription.setText(description);

            // 👉 শুধু যদি description বড় হয়, তখন see more দেখাও
            productDescription.post(() -> {
                if (productDescription.getLineCount() > 3) {
                    seeMore.setVisibility(View.VISIBLE);
                } else {
                    seeMore.setVisibility(View.GONE);
                }
            });

            seeMore.setOnClickListener(v -> {
                if (isExpanded) {
                    productDescription.setMaxLines(3);
                    seeMore.setText("See more");
                    isExpanded = false;
                } else {
                    productDescription.setMaxLines(Integer.MAX_VALUE);
                    seeMore.setText("See less");
                    isExpanded = true;
                }
            });

        } else {
            productDescription.setText("No description available");
            seeMore.setVisibility(View.GONE);
        }

        productImage.setImageResource(imageResId);
    }


    private void loadStoreInfo() {
        storeName.setText("Polli Bangla Super Markt");
        storeAddress.setText("Dressendorferstrasse 58");
        contact.setText("06781216818");
        hotline.setText("Monday - Friday, 10AM-5PM");
    }

    private void showImageDialog(Object imageSource) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_image_fullscreen);

        PhotoView photoView = dialog.findViewById(R.id.dialogImageView);
        ImageView closeBtn = dialog.findViewById(R.id.btnClose);

        if (imageSource instanceof Integer) {
            photoView.setImageResource((int) imageSource);
        } else if (imageSource instanceof String) {
            Glide.with(this)
                    .load((String) imageSource)
                    .placeholder(R.drawable.ic_broken_image)
                    .error(R.drawable.ic_broken_image)
                    .into(photoView);
        }

        closeBtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

//    private void loadProductDetailsFromApi(int productId) {
//        ProductDetailsApi api = RetrofitClient.getInstance().create(ProductDetailsApi.class);
//
//        api.getProductById(productId).enqueue(new Callback<ProductDetails>() {
//            @Override
//            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    ProductDetails product = response.body();
//
//                    productTitle.setText(product.getName());
//                    productPrice.setText("\u20AC " + product.getPrice());
//                    productDescription.setText(product.getDescription());
//
//                    if (product.getImageUrl() != null) {
//                        Glide.with(ProductDetailsActivity.this)
//                                .load(product.getImageUrl())
//                                .placeholder(R.drawable.placeholder)
//                                .into(productImage);
//                    }
//                } else {
//                    Toast.makeText(ProductDetailsActivity.this, "Failed to load product", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProductDetails> call, Throwable t) {
//                Toast.makeText(ProductDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }




    private void setupRecyclerView() {
        productRecyclerView = findViewById(R.id.productRecyclerView);
        adapter = new StoreProductAdapter(relatedProducts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setAdapter(adapter);
    }

    private void loadRelatedProductsFromApi() {
        RelatedProductApi api = RetrofitClient.getInstance().create(RelatedProductApi.class);
        api.getRelatedProducts().enqueue(new Callback<List<StoreProduct>>() {
            @Override
            public void onResponse(Call<List<StoreProduct>> call, Response<List<StoreProduct>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    relatedProducts.clear();
                    relatedProducts.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "No related products found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<StoreProduct>> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }






}


