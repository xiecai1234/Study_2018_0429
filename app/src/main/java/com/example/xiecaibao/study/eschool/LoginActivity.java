package com.example.xiecaibao.study.eschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.eschool.h5.TestH5Activity;
import com.example.xiecaibao.study.eschool.h5.TestH5Activity2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.iv_guanbi)
    ImageView ivGuanbi;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_quick_register)
    TextView tvQuickRegister;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.rl_quick_regist_and_forget_pwd)
    RelativeLayout rlQuickRegistAndForgetPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_fingerlogin)
    TextView btnFingerlogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eschool_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_guanbi, R.id.tv_quick_register, R.id.tv_forget_pwd, R.id.btn_login, R.id.btn_fingerlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guanbi:
                finish();
                break;
            case R.id.tv_quick_register:
                break;
            case R.id.tv_forget_pwd:
                break;
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, TestH5Activity.class));
                break;
            case R.id.btn_fingerlogin:
                startActivity(new Intent(LoginActivity.this, TestH5Activity2.class));
                break;
        }
    }
}
