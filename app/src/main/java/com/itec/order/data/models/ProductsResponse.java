package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public class ProductsResponse extends BaseResponse {
    @SerializedName("produse")
    public List<Product> products;
}
