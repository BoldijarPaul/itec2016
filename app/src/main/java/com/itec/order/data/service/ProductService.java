package com.itec.order.data.service;

import com.itec.order.data.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Paul on 5/13/2016.
 */
public interface ProductService {

    @GET("/products")
    Call<ProductsResponse> getProducts();

}
