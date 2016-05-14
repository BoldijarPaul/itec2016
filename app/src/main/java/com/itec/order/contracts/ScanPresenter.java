package com.itec.order.contracts;

import android.util.Log;

import com.google.gson.Gson;
import com.itec.order.data.models.NfcBody;
import com.itec.order.data.models.QrBody;
import com.itec.order.data.models.TableBody;
import com.itec.order.data.models.TableResponse;
import com.itec.order.data.service.OrderService;
import com.itec.order.data.service.RetrofitUtils;

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
        findTable(new QrBody(qr));
    }

    public void findTablefromNfc(String nfc) {
        findTable(new NfcBody(nfc));
    }

    private void findTable(TableBody tableBody) {
        Log.d("table", new Gson().toJson(tableBody));
        if (mLoading) {
            return;
        }
        mLoading = true;
        Call<TableResponse> orderServiceCall = mOrderService.getTable(tableBody);
        orderServiceCall.enqueue(new Callback<TableResponse>() {
            @Override
            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {
                mLoading = false;
                if (response.body() != null && "ok".equals(response.body().status)) {
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
        });
    }
}
