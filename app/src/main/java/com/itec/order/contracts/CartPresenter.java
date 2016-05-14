package com.itec.order.contracts;

import com.itec.order.data.models.Order;
import com.itec.order.data.models.OrderResponse;
import com.itec.order.data.persistance.CurrentCartProduct;
import com.itec.order.data.service.OrderService;
import com.itec.order.data.service.RetrofitUtils;
import com.itec.order.ui.app.BaseApp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Paul on 5/14/2016.
 */
public class CartPresenter extends Presenter<CartView> {
    private OrderService mOrderService;

    public CartPresenter(CartView view) {
        super(view);
        mOrderService = RetrofitUtils.getRetrofit().create(OrderService.class);
    }

    public void sendOrder() {
        List<CurrentCartProduct> products = CurrentCartProduct.listAll(CurrentCartProduct.class);
        List<Order> orders = new ArrayList<>();
        for (CurrentCartProduct cartProduct : products) {
            orders.add(new Order(cartProduct));
        }
        Call<OrderResponse> orderResponseCall = mOrderService.getProducts(BaseApp.getToken().get(),
                12,
                orders);
        orderResponseCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if ("ok".equals(response.body().status)) {
                    getView().showOrderSuccessfull();
                } else {
                    getView().showError();
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                getView().showNetworkError();
            }
        });

    }
}
