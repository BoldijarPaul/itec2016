package com.itec.order.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bjz on 5/13/2016.
 */
public class LoginBody {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
}
