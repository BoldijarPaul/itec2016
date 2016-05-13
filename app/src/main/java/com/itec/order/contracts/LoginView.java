package com.itec.order.contracts;

/**
 * Created by bjz on 5/13/2016.
 */
public interface LoginView {
    void showNetworkError();

    void showLoginError();

    void loginSuccesful(int token);
}
