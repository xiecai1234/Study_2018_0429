package com.example.xiecaibao.study.mvp.biz;

import com.example.xiecaibao.study.mvp.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
