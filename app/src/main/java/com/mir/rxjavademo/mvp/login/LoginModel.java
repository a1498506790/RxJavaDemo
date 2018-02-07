package com.mir.rxjavademo.mvp.login;

import com.mir.rxjavademo.bean.UserBean;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public interface LoginModel {

    void login(String mobile, String password, LoginCallback loginCallback);

    interface LoginCallback{
        void loginSuccess(UserBean userBean);
        void loginFail(String msg);
    }

}
