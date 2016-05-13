package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjz on 5/13/2016.
 */
public class LoginResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("ok")
    public String ok;
    @SerializedName("user_id")
    public String userId;
}
