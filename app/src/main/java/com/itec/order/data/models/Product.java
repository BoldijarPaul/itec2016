package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public class Product {
    @SerializedName("description")
    public String description;
    @SerializedName("id")
    public int id;
    @SerializedName("image_src_id")
    public String image;
    @SerializedName("category_id")
    public int categoryId;

    public Product(String description, int id, String image, int categoryId) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.categoryId = categoryId;
    }

    public Product() {

    }

    public static Product mock() {
        Product product = new Product();
        product.categoryId = 1;
        product.id = 2;
        product.description = "Bucovina 10 ml";
        product.image = Math.random() > .5 ? "http://botosaninews.ro/wp-content/uploads/2016/01/coca-cola.jpg" : "http://weknowyourdreamz.com/images/soup/soup-06.jpg";
        return product;
    }

    public static List<Product> mockList(int length) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            products.add(mock());
        }
        return products;
    }
}
