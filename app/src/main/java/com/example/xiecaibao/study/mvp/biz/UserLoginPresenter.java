package com.example.xiecaibao.study.mvp.biz;

import android.os.Handler;

import com.example.xiecaibao.study.mvp.bean.User;


public class UserLoginPresenter {
    private IUserBiz iUserBiz;
    private IUserLoginView iPresenter;
    private Handler handler = new Handler();
    public UserLoginPresenter(IUserLoginView iPresenter) {
        this.iUserBiz = new UserBiz();
        this.iPresenter = iPresenter;
    }

    public void login() {
        iPresenter.showLoadingProgressbar();
        iUserBiz.onLogin(iPresenter.getUserName(), iPresenter.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iPresenter.toMainActivity(user);
                        iPresenter.hideLoadingProgressbar();
                    }
                });
            }

            @Override
            public void loginFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iPresenter.showFailedError();
                        iPresenter.hideLoadingProgressbar();
                    }
                });
            }
        });
    }
}
