package com.mir.rxjavademo.mvp.login;

import android.text.TextUtils;

import com.mir.rxjavademo.bean.UserBean;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginModel mLoginModel;
    private LoginContract.View mView;

    public LoginPresenter(LoginModel loginModel, LoginContract.View view) {
        mLoginModel = loginModel;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public boolean checkUserInfo(String mobile, String password) {
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    @Override
    public void login(String mobile, String password) {
        mView.showProgress();
        mLoginModel.login(mobile, password, new LoginModel.LoginCallback() {
            @Override
            public void loginSuccess(UserBean userBean) {
                mView.hintProgress();
                mView.loginSuccess(userBean);
            }

            @Override
            public void loginFail(String msg) {
                mView.hintProgress();
                mView.loginFail(msg);
            }
        });
    }

    @Override
    public void start() {}
}
