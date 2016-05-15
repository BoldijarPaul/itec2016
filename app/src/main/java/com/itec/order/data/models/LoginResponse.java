package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjz on 5/13/2016.
 */
public class LoginResponse extends BaseResponse {
    @SerializedName("message")
    public String message;
    @SerializedName("user_id")
    public int userId;
}
