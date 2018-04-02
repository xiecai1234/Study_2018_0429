package com.example.xiecaibao.study.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.mvp.bean.User;
import com.example.xiecaibao.study.mvp.biz.IUserLoginView;
import com.example.xiecaibao.study.mvp.biz.UserLoginPresenter;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView {
    EditText editTextUserName;
    EditText editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);
        initView();
    }

    private void initView() {
        editTextUserName = findViewById(R.id.et_user_name);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressbar_loading);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });
    }

    @Override
    public String getUserName() {
        return editTextUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return editTextPassword.getText().toString();
    }

    @Override
    public void showLoadingProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgressbar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getName() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, " login failed", Toast.LENGTH_SHORT).show();
    }
}
