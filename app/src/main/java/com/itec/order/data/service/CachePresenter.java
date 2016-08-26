package com.itec.order.data.service;

import com.itec.order.data.ImageUtils;
import com.itec.order.data.models.Category;
import com.itec.order.data.models.Product;
import com.itec.order.data.models.ProductsResponse;
import com.itec.order.data.persistance.CategoryRecord;
import com.itec.order.data.persistance.FullProductRecord;

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
        new CategoryRecord(new Category(1, ImageUtils.getRandomImage(), "Sucuri", false)).save();
        new CategoryRecord(new Category(2, ImageUtils.getRandomImage(), "Mancare", false)).save();

        /*
        final Call<CategoriesResponse> categoriesResponseCall = mProductService.getCategories();
        categoriesResponseCall.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                CategoryRecord.deleteAll(CategoryRecord.class);
                if (response.body() == null) {
                    return;
                }
                List<Category> categories = response.body().categories;
                for (Category category : categories) {
                    new CategoryRecord(category).save();
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
    }

        );
        */
    }

    public void startCaching() {
        loadCategories();
        loadProducts();
    }

    private void loadProducts() {
        FullProductRecord.deleteAll(FullProductRecord.class);
        new FullProductRecord(new Product("Coca Cola 500 Ml", 1, ImageUtils.getRandomImage(), 1)).save();
        new FullProductRecord(new Product("Fanta 250 ML", 2, ImageUtils.getRandomImage(), 1)).save();
        new FullProductRecord(new Product("Vodka 100 ML", 3, ImageUtils.getRandomImage(), 1)).save();
        new FullProductRecord(new Product("Iaurt Frappe", 4, ImageUtils.getRandomImage(), 1)).save();
        new FullProductRecord(new Product("Porc 100 g", 5, ImageUtils.getRandomImage(), 2)).save();
        new FullProductRecord(new Product("Scoici 150g & Cartofi pai 100g", 6, ImageUtils.getRandomImage(), 2)).save();

        Call<ProductsResponse> productsResponseCall = mProductService.getProducts();
        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.body() == null) {
                    return;
                }
                List<Product> products = response.body().products;
                FullProductRecord.deleteAll(FullProductRecord.class);
                for (Product product : products) {
                    product.image = ImageUtils.getRandomImage();
                    new FullProductRecord(product).save();
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }

}
