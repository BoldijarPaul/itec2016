package com.itec.order.data.service;

import com.itec.order.data.models.Order;
import com.itec.order.data.models.OrderResponse;
import com.itec.order.data.models.TableBody;
import com.itec.order.data.models.TableResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Paul on 5/14/2016.
 */
public interface OrderService {

    @POST("/order/{userId}/{tableId}")
    Call<OrderResponse> getProducts(@Path("userId") int userId,
                                    @Path("tableId") int tableId,
                                    @Body List<Order> orderList);

    @POST("/table")
    @Headers("Content-Type: application/json")
    Call<TableResponse> getTable(@Body TableBody qrBody);

}
