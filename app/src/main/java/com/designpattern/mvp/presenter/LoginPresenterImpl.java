package com.designpattern.mvp.presenter;

import android.util.Log;
import com.designpattern.mvp.model.Result;
import com.designpattern.mvp.network.AsyncHttp;
import com.designpattern.mvp.view.LoginView;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void handleLogin(String email, String password) {
        apiDoLogin(email, password);
    }

    private void apiDoLogin(final String email, final String password) {

        AsyncHttp.get(AsyncHttp.API_USER_LOGIN, AsyncHttp.paramsUserLogin(email, password), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("@@@","responseString "+responseString);
                Result result = new Result("Error " + statusCode);
                loginView.onLoginFailure(result);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("@@@","responseString "+responseString);
                Result result = new Gson().fromJson(responseString, Result.class);
                if (result != null) {
                    loginView.onLoginSuccess(result);
                }
            }
        });
    }
}