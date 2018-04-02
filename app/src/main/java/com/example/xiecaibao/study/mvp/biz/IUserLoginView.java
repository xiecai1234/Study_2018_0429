package com.example.xiecaibao.study.mvp.biz;

import com.example.xiecaibao.study.mvp.bean.User;

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void showLoadingProgressbar();

    void hideLoadingProgressbar();

    void toMainActivity(User user);

    void showFailedError();
}
