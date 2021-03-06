package com.itec.order.contracts;

import com.itec.order.data.models.LoginBody;
import com.itec.order.data.models.LoginResponse;
import com.itec.order.data.service.LoginService;
import com.itec.order.data.service.RetrofitUtils;
import com.itec.order.ui.app.BaseApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bjz on 5/13/2016.
 */
public class LoginPresenter extends Presenter<LoginView> {
    private LoginService mLoginService;

    public LoginPresenter(LoginView view) {
        super(view);
        mLoginService = RetrofitUtils.getRetrofit().create(LoginService.class);
    }

    public void doLogin(final String email, String password) {
        LoginBody body = new LoginBody();
        body.email = email;
        body.password = password;
        getView().loginSuccesful(1);
        /*
        Call<LoginResponse> responseCall = mLoginService.doLogin(body);

        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    getView().showLoginError();
                    return;
                }
                if ("ok".equals(loginResponse.status)) {
                    BaseApp.getEmail().set(email);
                    getView().loginSuccesful(loginResponse.userId);
                } else {
                    getView().showLoginError();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                getView().showNetworkError();
            }
        });
        */
    }

}
