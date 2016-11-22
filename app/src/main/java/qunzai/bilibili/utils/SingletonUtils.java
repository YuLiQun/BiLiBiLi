package qunzai.bilibili.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by XingMingDa on 16/11/22.
 * 单例的实现类
 */

public enum SingletonUtils {

    INSTANCE;

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;

    SingletonUtils(){
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    public OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Gson getGson() {
        return mGson;
    }
}
