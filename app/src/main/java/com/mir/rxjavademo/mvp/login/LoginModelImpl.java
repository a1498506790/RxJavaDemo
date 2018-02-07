package com.mir.rxjavademo.mvp.login;

import com.mir.rxjavademo.base.BaseBean;
import com.mir.rxjavademo.bean.UserBean;
import com.mir.rxjavademo.http.HttpClient;
import com.mir.rxjavademo.http.api.UserService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public class LoginModelImpl implements LoginModel {

    @Override
    public void login(String mobile, String password, final LoginCallback loginCallback) {
        HttpClient.getIns().createService(UserService.class)
                .login(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(BaseBean<UserBean> value) {
                        if(value.getStatus() == 0){
                            loginCallback.loginSuccess(value.getData());
                        }else{
                            loginCallback.loginFail(value.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallback.loginFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {}
                });

    }

}
