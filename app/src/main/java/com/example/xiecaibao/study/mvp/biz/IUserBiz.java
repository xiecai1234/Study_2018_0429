package com.example.xiecaibao.study.mvp.biz;

public interface IUserBiz {
    void onLogin(String userName, String password, OnLoginListener listener);
}
