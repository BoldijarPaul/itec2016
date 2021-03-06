package com.itec.order.contracts;

import com.itec.order.data.BillProduct;
import com.itec.order.data.ImageUtils;
import com.itec.order.data.models.PayBody;
import com.itec.order.data.service.OrderService;
import com.itec.order.data.service.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/15/2016.
 */
public class PayPresenter extends Presenter<PayView> {
    private OrderService mOrderService;

    public PayPresenter(PayView view) {
        super(view);
        mOrderService = RetrofitUtils.getRetrofit().create(OrderService.class);
    }

    public void pay(List<PayBody> payBodies) {
        /*
        Call<BaseResponse> baseResponseCall = mOrderService.payProducts(BaseApp.getToken().get(),
                BaseApp.getTableId().get(),
                payBodies);
        baseResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() == null || !"ok".equals(response.body().status)) {
                    getView().showError();
                    return;
                }*/
        getView().showSuccessPay();
          /*  }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                getView().showNetworkError();
            }
        });*/
    }

    public void loadBillProducts() {
        List<BillProduct> billProducts = new ArrayList<>();
        billProducts.add(new BillProduct(1, 1, 1, (float) (Math.random() * 100), "Cola", ImageUtils.getRandomImage(), "Sucuri"));
        billProducts.add(new BillProduct(1, 1, 1, (float) (Math.random() * 100), "Fanta", ImageUtils.getRandomImage(), "Sucuri"));
        billProducts.add(new BillProduct(1, 1, 1, (float) (Math.random() * 100), "Apa", ImageUtils.getRandomImage(), "Sucuri"));
        billProducts.add(new BillProduct(1, 1, 1, (float) (Math.random() * 100), "Dew", ImageUtils.getRandomImage(), "Sucuri"));
        billProducts.add(new BillProduct(1, 1, 1, (float) (Math.random() * 100), "Bere", ImageUtils.getRandomImage(), "Sucuri"));
        getView().showProducts(billProducts);
/*
        Call<List<BillProduct>> products = mOrderService.getBill(BaseApp.getTableId().get());
        products.enqueue(new Callback<List<BillProduct>>() {
            @Override
            public void onResponse(Call<List<BillProduct>> call, Response<List<BillProduct>> response) {
                if (response.body() == null) {
                    getView().showError();
                    return;
                }
                getView().showProducts(response.body());
            }

            @Override
            public void onFailure(Call<List<BillProduct>> call, Throwable t) {
                getView().showNetworkError();
            }
        });*/
    }
}
