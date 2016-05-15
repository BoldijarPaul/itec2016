package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public class CategoriesResponse extends BaseResponse {
    @SerializedName("categories")
    public List<Category> categories;
}
