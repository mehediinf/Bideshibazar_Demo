package com.mtach.bideshibazar.network;

import com.mtach.bideshibazar.product.ProductDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductDetailsApi {
    @GET("product/{id}")
    Call<ProductDetails> getProductById(@Path("id") int productId);
}