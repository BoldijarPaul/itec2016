package com.itec.order.data.service;

import com.itec.order.data.models.LoginBody;
import com.itec.order.data.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by bjz on 5/13/2016.
 */
public interface LoginService {
    @POST("/user")
    Call<LoginResponse> doLogin(@Body LoginBody body);


}
