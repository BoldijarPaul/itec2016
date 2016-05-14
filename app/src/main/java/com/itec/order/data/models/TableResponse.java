package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paul on 5/14/2016.
 */
public class TableResponse extends BaseResponse {
    @SerializedName("tableId")
    public int tableId;
}
