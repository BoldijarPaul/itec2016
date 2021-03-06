package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 5/13/2016.
 */
public class Category {
    @SerializedName("id")
    public int id;
    @SerializedName("image_src_id")
    public String image;
    @SerializedName("description")
    public String description;

    public boolean checked;

    public Category() {
    }

    public Category(int id, String image, String description, boolean checked) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.checked = checked;
    }
}
