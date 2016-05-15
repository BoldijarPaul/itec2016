package com.itec.order.contracts;

import com.itec.order.data.BillProduct;

import java.util.List;

/**
 * Created by Paul on 5/15/2016.
 */
public interface PayView {
    void showProducts(List<BillProduct> billProducts);

    void showNetworkError();

    void showError();

    void showSuccessPay();
}
