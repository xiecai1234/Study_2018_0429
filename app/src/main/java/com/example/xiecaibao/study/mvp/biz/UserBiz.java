package com.example.xiecaibao.study.mvp.biz;

import com.example.xiecaibao.study.mvp.bean.User;

public class UserBiz implements IUserBiz {
    @Override
    public void onLogin(final String userName, final String password, final OnLoginListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("xcb".equals(userName) && "123".equals(password)) {
                    listener.loginSuccess(new User(userName, password));
                } else {
                    listener.loginFailed();
                }
            }
        }).start();
    }
}
