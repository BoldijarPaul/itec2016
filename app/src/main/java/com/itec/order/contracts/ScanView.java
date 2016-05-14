package com.itec.order.contracts;

/**
 * Created by Paul on 5/14/2016.
 */
public interface ScanView {
    void showNetworkError();

    void showError();

    void showTable(int tableId);
}
