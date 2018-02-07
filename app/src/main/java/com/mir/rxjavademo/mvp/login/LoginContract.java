package com.mir.rxjavademo.mvp.login;

import com.mir.rxjavademo.base.BasePresenter;
import com.mir.rxjavademo.base.BaseView;
import com.mir.rxjavademo.bean.UserBean;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public interface LoginContract {

    interface View extends BaseView<LoginPresenter>{
        void showProgress();
        void hintProgress();
        void loginSuccess(UserBean userBean);
        void loginFail(String msg);
    }

    interface Presenter extends BasePresenter{
        boolean checkUserInfo(String mobile, String password);
        void login(String mobile, String password);
    }

}
