package qunzai.bilibili.internet;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import qunzai.bilibili.utils.SingletonUtils;

/**
 * Created by XingMingDa on 16/11/28.
 * OkHttp的网络请求
 */

public class OkHttpManager extends NetworkManager {

    private Handler mHandler;

    public OkHttpManager() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected <T> void get(String url, Class<T> clazz, ResponseCallBack<T> responseCallBack) {
        //构建Request对象
        Request request = new Request.Builder().url(url).build();
        sendHttpRequest(request, clazz, responseCallBack);
    }

    @Override
    protected <T> void post(String url, Class<T> clazz, ResponseCallBack<T> responseCallBack, HashMap<String, String> body) {
        FormBody.Builder formBuilder = new FormBody.Builder();

        for (String s : body.keySet()) {
            formBuilder.add(s, body.get(s));
        }
        //处理完了 post请求的 body部分
        FormBody formBody = formBuilder.build();

        Request postRequest = new Request
                .Builder()
                .url(url)
                .post(formBody)//把body放到request里
                .build();

        sendHttpRequest(postRequest, clazz, responseCallBack);

    }

    //专门用来发起请求
    @Override
    protected <T> void sendHttpRequest(Request request, final Class<T> clazz, final ResponseCallBack<T> responseCallBack) {
        //发起网络请求
        SingletonUtils.INSTANCE.getmOkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        //网络请求失败
                        mHandler.post(new ErrorRunnable<T>(responseCallBack, e));
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //网络请求成功
                        String data = response.body().string();
                        //尝试解析
                        try {//防止因为奇葩的数据 导致解析失败
                            T t = SingletonUtils.INSTANCE.getGson().fromJson(data,clazz);
                            mHandler.post(new ResponseRunnable<T>(responseCallBack, t));
                        } catch (Exception e) {
                            e.printStackTrace();//把错误信息 直接输出
                            mHandler.post(new ErrorRunnable<T>(responseCallBack,e));
                        }
                    }
                });
    }

    private class ResponseRunnable<T> extends NetworkRunnable<T>{
        private T t;

        public ResponseRunnable(ResponseCallBack<T> responseCallBack,T t) {
            super(responseCallBack);
            this.t = t;
        }

        @Override
        public void run() {
            mResponseCallBack.onResponse(t);
        }
    }

    private class ErrorRunnable<T> extends NetworkRunnable<T>{
        private Exception e;

        public ErrorRunnable(ResponseCallBack<T> responseCallBack,Exception e) {
            super(responseCallBack);
            this.e = e;
        }

        @Override
        public void run() {
            mResponseCallBack.onError(e);
        }
    }
}
