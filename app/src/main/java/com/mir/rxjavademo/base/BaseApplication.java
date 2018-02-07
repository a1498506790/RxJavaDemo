package com.mir.rxjavademo.base;

import android.app.Application;
import android.content.Context;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public class BaseApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
