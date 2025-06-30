package com.mtach.bideshibazar.network;

import com.mtach.bideshibazar.store.StoreProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RelatedProductApi {
    @GET("related-products") // 🟡 তোমার actual endpoint বসাও এখানে
    Call<List<StoreProduct>> getRelatedProducts();
}
