package com.itec.order.data.service;

import com.itec.order.data.models.CategoriesResponse;
import com.itec.order.data.models.Category;
import com.itec.order.data.models.Product;
import com.itec.order.data.models.ProductsResponse;
import com.itec.order.data.persistance.CategoryRecord;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Paul on 5/13/2016.
 */
public class CachePresenter {
    private ProductService mProductService;

    public CachePresenter() {
        mProductService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    private void loadCategories() {
        final Call<CategoriesResponse> categoriesResponseCall = mProductService.getCategories();
        categoriesResponseCall.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                CategoryRecord.deleteAll(CategoryRecord.class);
                List<Category> categories = response.body().categories;
                for (Category category : categories) {
                    new CategoryRecord(category).save();
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }

    public void startCaching() {
        loadCategories();
        loadProducts();
    }

    private void loadProducts() {
        Call<ProductsResponse> productsResponseCall = mProductService.getProducts();
        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                List<Product> products = response.body().products;
                for (Product product : products) {

                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }

}
