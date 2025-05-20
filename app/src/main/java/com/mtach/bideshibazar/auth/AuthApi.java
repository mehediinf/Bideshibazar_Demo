package com.mtach.bideshibazar.auth;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/auth/register")
    Call<ResponseBody> registerUser(@Body JsonObject body);

    @POST("api/auth/login")
    Call<ResponseBody> loginUser(@Body JsonObject body);

    @GET("api/dashboard")
    Call<ResponseBody> getDashboard(@Header("Authorization") String token);
}

