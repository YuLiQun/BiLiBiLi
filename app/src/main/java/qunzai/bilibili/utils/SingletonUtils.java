package qunzai.bilibili.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by XingMingDa on 16/11/22.
 * 单例的实现类
 */

public enum SingletonUtils {

    INSTANCE;

    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    private ThreadPoolExecutor mThreadPoolExecutor;

    SingletonUtils(){
        //OkHttp实例化
        mOkHttpClient = new OkHttpClient();
        //Gson实例化
        mGson = new Gson();
        //线程池实例化
        int cupCore = Runtime.getRuntime().availableProcessors();
        mThreadPoolExecutor = new ThreadPoolExecutor(cupCore + 1
                ,cupCore * 2 + 1
                , 60l
                , TimeUnit.SECONDS
                ,new LinkedBlockingDeque<Runnable>());
    }

    public OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }

    public Gson getGson() {
        return mGson;
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return mThreadPoolExecutor;
    }
}
