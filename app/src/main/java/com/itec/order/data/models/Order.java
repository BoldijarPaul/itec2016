package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;
import com.itec.order.data.persistance.CurrentCartProduct;

/**
 * Created by Paul on 5/14/2016.
 */
public class Order {
    @SerializedName("productId")
    public int productId;
    @SerializedName("quantity")
    public int quantity;

    public Order() {
    }

    public Order(CurrentCartProduct currentCartProduct) {
        productId = currentCartProduct.productId;
        quantity = currentCartProduct.amount;
    }
}
