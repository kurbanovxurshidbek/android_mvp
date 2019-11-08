package com.designpattern.mvp.view;

import com.designpattern.mvp.model.Result;

public interface LoginView {

    void onLoginSuccess(Result result);
    void onLoginFailure(Result result);

}
