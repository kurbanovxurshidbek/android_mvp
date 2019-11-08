package com.designpattern.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.designpattern.mvp.R;
import com.designpattern.mvp.model.Result;
import com.designpattern.mvp.presenter.LoginPresenter;
import com.designpattern.mvp.presenter.LoginPresenterImpl;
import com.designpattern.mvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText et_email, et_password;
    private Button b_login;
    private TextView tv_error;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    @Override
    public void onLoginFailure(Result result) {
        tv_error.setText("Error " + result.getMessage());
    }

    @Override
    public void onLoginSuccess(Result result) {
        if (result.getStatus().equals("0")) {
            callMainActivity();
        } else {
            tv_error.setText(result.getMessage());
        }
    }

    /*
     * Methods
     **/

    private void initViews() {

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        b_login = findViewById(R.id.b_login);
        tv_error = findViewById(R.id.tv_error);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                presenter.handleLogin(email, password);
            }
        });

        presenter = new LoginPresenterImpl(this);
    }

    private void callMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
