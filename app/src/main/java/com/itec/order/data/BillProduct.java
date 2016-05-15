package com.itec.order.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 5/15/2016.
 */
public class BillProduct {
    @SerializedName("product_id")
    public int productId;
    @SerializedName("delivered_order_id")
    public int deliverOrderId;
    @SerializedName("unit_price")
    public float unitPrice;
    @SerializedName("quantity")
    public int quantity;
    @SerializedName("price")
    public float price;
    @SerializedName("email")
    public String email;
    @SerializedName("client_id")
    public int clientId;
    @SerializedName("product_name")
    public String productName;

    public String image;
    public String category;
}
