package com.itec.order.contracts;

import com.itec.order.data.models.Order;
import com.itec.order.data.models.OrderResponse;
import com.itec.order.data.persistance.CurrentCartProduct;
import com.itec.order.data.persistance.OrderProductRecord;
import com.itec.order.data.persistance.OrderRecord;
import com.itec.order.data.service.OrderService;
import com.itec.order.data.service.RetrofitUtils;
import com.itec.order.ui.app.BaseApp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

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
        int mockId = (int) (Math.random() * 100);
        OrderRecord record = new OrderRecord(mockId);
        List<CurrentCartProduct> currentCartProducts = CurrentCartProduct.listAll(CurrentCartProduct.class);
        for (CurrentCartProduct currentCartProduct : currentCartProducts) {
            OrderProductRecord productRecord = new OrderProductRecord(currentCartProduct, mockId);
            productRecord.save();
        }
        record.save();

        BaseApp.getNote().delete();
        CurrentCartProduct.deleteAll(CurrentCartProduct.class);
        getView().showOrderSuccessfull();
        /*
        orderResponseCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if ("ok".equals(response.body().status)) {
                    if (response.body().orderIds != null && response.body().orderIds.size() > 0) {
                        OrderRecord record = new OrderRecord(
                                response.body().orderIds.get(0));
                        List<CurrentCartProduct> currentCartProducts = CurrentCartProduct.listAll(CurrentCartProduct.class);
                        for (CurrentCartProduct currentCartProduct : currentCartProducts) {
                            OrderProductRecord productRecord = new OrderProductRecord(currentCartProduct, response.body().orderIds.get(0));
                            productRecord.save();
                        }
                        record.save();
                    }
                    BaseApp.getNote().delete();
                    CurrentCartProduct.deleteAll(CurrentCartProduct.class);
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
*/
    }
}
