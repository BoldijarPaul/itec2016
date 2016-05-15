package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrderResponse extends BaseResponse {
    @SerializedName("orders_ids")
    public List<Integer> orderIds;
}
