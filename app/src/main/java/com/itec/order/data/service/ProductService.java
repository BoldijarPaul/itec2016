package com.itec.order.data.service;

import com.itec.order.data.models.CategoriesResponse;
import com.itec.order.data.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Paul on 5/13/2016.
 */
public interface ProductService {

    @GET("/products")
    Call<ProductsResponse> getProducts();

    @GET("/products/{path}")
    Call<ProductsResponse> getProducts(@Path("path") int category);

    @GET("/categories")
    Call<CategoriesResponse> getCategories();
}
