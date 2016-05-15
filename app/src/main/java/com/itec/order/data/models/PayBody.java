package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 5/15/2016.
 */
public class PayBody {
    @SerializedName("delivered_order_id")
    public int deliveredOrderId;
    @SerializedName("quantity")
    public int quantity;

    public PayBody() {
    }

    public PayBody(int deliveredOrderId, int quantity) {
        this.deliveredOrderId = deliveredOrderId;
        this.quantity = quantity;
    }
}
