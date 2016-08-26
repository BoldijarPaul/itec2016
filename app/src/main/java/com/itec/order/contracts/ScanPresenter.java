package com.itec.order.contracts;

import com.itec.order.data.models.NfcBody;
import com.itec.order.data.models.QrBody;
import com.itec.order.data.models.TableResponse;
import com.itec.order.data.service.OrderService;
import com.itec.order.data.service.RetrofitUtils;
import com.itec.order.ui.app.BaseApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Paul on 5/14/2016.
 */
public class ScanPresenter extends Presenter<ScanView> {

    private OrderService mOrderService;
    private boolean mLoading = false;

    public ScanPresenter(ScanView view) {
        super(view);
        mOrderService = RetrofitUtils.getRetrofit().create(OrderService.class);
    }

    public void findTableFromQr(String qr) {
        BaseApp.getTableId().set(1);
        getView().showTable(1);
       /* if (mLoading) {
            return;
        }
        mLoading = true;
        Call<TableResponse> orderServiceCall = mOrderService.getTable(new QrBody(qr));
        orderServiceCall.enqueue(new Callback<TableResponse>() {
            @Override
            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {
                mLoading = false;
                if (response.body() != null && "ok".equals(response.body().status)) {
                    BaseApp.getTableId().set(response.body().tableId);
                    getView().showTable(response.body().tableId);
                } else {
                    getView().showError();
                }
            }

            @Override
            public void onFailure(Call<TableResponse> call, Throwable t) {
                mLoading = false;
                getView().showNetworkError();
            }
        });*/
    }

    public void findTableFromNfc(String nfc) {
        BaseApp.getTableId().set(1);
        getView().showTable(1);
        /*if (mLoading) {
            return;
        }
        mLoading = true;

        Call<TableResponse> orderServiceCall = mOrderService.getTable(new NfcBody(nfc));
        orderServiceCall.enqueue(new Callback<TableResponse>() {
            @Override
            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {
                mLoading = false;
                if (response.body() != null && "ok".equals(response.body().status)) {
                    BaseApp.getTableId().set(response.body().tableId);
                    getView().showTable(response.body().tableId);
                } else {
                    getView().showError();
                }
            }

            @Override
            public void onFailure(Call<TableResponse> call, Throwable t) {
                mLoading = false;
                getView().showNetworkError();
            }
        });*/
    }
}
