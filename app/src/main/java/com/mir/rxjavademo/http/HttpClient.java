package com.mir.rxjavademo.http;

import android.util.Log;

import com.mir.rxjavademo.base.BaseApplication;
import com.mir.rxjavademo.constants.Api;
import com.mir.rxjavademo.constants.AppConfig;
import com.mir.rxjavademo.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author by lx
 * @github https://github.com/a1498506790
 * @data 2018/2/7
 * @desc
 */

public class HttpClient {

    private Retrofit mRetrofit;
    private static HttpClient mInstance = null;
    private static final boolean mIsSetCache = true;

    public static HttpClient getIns(String baseUrl){
        if (mInstance == null) {
            synchronized (HttpClient.class){
                if (mInstance == null) {
                    mInstance = new HttpClient(baseUrl);
                }
            }
        }
        return mInstance;
    }

    public static HttpClient getIns(){
        return getIns(null);
    }

    public HttpClient(String baseUrl){
        if (baseUrl != null) {
            configRetrofit(baseUrl);
        }else{
            configRetrofit(Api.API_SERVICE_URL);
        }
    }

    private void configRetrofit(String baseUrl) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (mIsSetCache) {
            File cacheFile = new File(AppConfig.CACHE_PATH);
            Cache cache = new Cache(cacheFile, AppConfig.CACHE_DATE);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Log.e("test", "url : " + request.url());
                    if (!NetUtils.networkIsAvailable(BaseApplication.getContext())) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    Response response = chain.proceed(request);
                    if (NetUtils.networkIsAvailable(BaseApplication.getContext())) {
                        int maxAge = 0;
                        // 有网络时 设置缓存超时时间0个小时
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("nyn")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("nyn")
                                .build();
                    }
                    return response;
                }
            };
            builder.cache(cache).addInterceptor(cacheInterceptor);
        }

        // 设置公共参数
        // 设置头
        // 设置Log信息拦截器
        // 设置Cookie
        builder.cookieJar(cookieJar);

        // 设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        // 设置错误重连
        builder.retryOnConnectionFailure(true);

        // 构建Retrofit
        OkHttpClient okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public <T> T createService(Class<T> clz){
        return mRetrofit.create(clz);
    }


    public static final Map<String, List<Cookie>> cookieStore = new HashMap<>();
    public static final CookieJar cookieJar = new CookieJar() {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(url.host(), cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url.host());
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };

}
