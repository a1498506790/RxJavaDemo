package com.mir.rxjavademo.http.api;

import com.mir.rxjavademo.base.BaseBean;
import com.mir.rxjavademo.bean.UserBean;
import com.mir.rxjavademo.constants.Api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public interface UserService {

    @GET(Api.LOGIN)
    Observable<BaseBean<UserBean>> login(@Query("mobile") String mobile, @Query("password") String password);

}
