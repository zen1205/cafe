package com.example.tuan4;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    // --- API CHO PRODUCT (ĐỒ UỐNG) ---
    @GET("products")
    Call<List<AdminProductModel>> getProducts();

    @POST("products")
    Call<AdminProductModel> createProduct(@Body AdminProductModel product);

    @PUT("products/{id}")
    Call<AdminProductModel> updateProduct(@Path("id") int productId, @Body AdminProductModel product);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") int productId);
}