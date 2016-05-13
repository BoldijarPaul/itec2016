package com.itec.order.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itec.app.R;
import com.itec.order.contracts.LoginPresenter;
import com.itec.order.contracts.LoginView;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginView {

    @Bind(R.id.login_email)
    EditText mLoginEmail;
    @Bind(R.id.login_password)
    EditText mLoginPassword;
    @Bind(R.id.login_button)
    TextView mLoginButton;

    private LoginPresenter mLoginPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginCicked();
            }
        });
        mLoginPresenter = new LoginPresenter(this);

    }

    private void LoginCicked() {
        mLoginPresenter.doLogin(mLoginEmail.getText().toString(), mLoginPassword.getText().toString());
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError() {
        Toast.makeText(getContext(), R.string.login_error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSuccesful(int token) {
        Toast.makeText(getContext(), R.string.login_succesful, Toast.LENGTH_SHORT).show();
    }
}
