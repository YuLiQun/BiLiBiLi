package qunzai.bilibili.internet;

import android.os.Handler;
import android.os.Looper;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/11/26.
 */
public class OkHttpManagerSingle {

    private static OkHttpManagerSingle okHttpManagerSingle;
    private OkHttpClient mClient;
    private Handler mHandler;//用来做线程的切换
    private Gson mGson;


    private OkHttpManagerSingle() {
        mClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());//保证在主线程
        mGson = new Gson();
    }



    public static OkHttpManagerSingle getInstance() {
        if (okHttpManagerSingle == null) {
            synchronized (OkHttpManagerSingle.class) {
                if (okHttpManagerSingle == null) {
                    okHttpManagerSingle = new OkHttpManagerSingle();
                }
            }
        }
        return okHttpManagerSingle;
    }


    /**
     * post请求
     *
     * @param url
     * @param clazz
     * @param responCallBack
     * @param body:这就是key，value
     * @param <Bean>
     */
    public <Bean> void post(String url, Class<Bean> clazz, ResponCallBack<Bean> responCallBack, HashMap<String, String> body) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String s : body.keySet()) {//body.keySet()
            //s是key，body是value，，，根据key取value
            builder.add(s, body.get(s));
        }
        //处理完了 post请求的body部分
        FormBody formBody = builder.build();
        Request postRequest = new Request.Builder().url(url).post(formBody).build();
        sendHttpRequest(postRequest, clazz, responCallBack);

    }


    //get请求，，
    public <Bean> void get(String url, final Class<Bean> clazz, final ResponCallBack<Bean> responCallBack) {
        Request request = new Request.Builder().url(url).build();

        sendHttpRequest(request,clazz,responCallBack);
    }

    //这个方法专门用来发起请求
    private <Bean> void sendHttpRequest(Request request, final Class<Bean> clazz, final ResponCallBack<Bean> responCallBack) {
        mClient.newCall(request).enqueue(new Callback() {
            //网络请求失败
            @Override
            public void onFailure(Call call, final IOException e) {


                mHandler.post(new ErrorRunnable<Bean>(responCallBack, e));

            }

            //网络请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String data = response.body().string();
                //尝试解析
                try {//防止因为奇葩的数据，导致解析失败
                    final Bean bean = mGson.fromJson(data, clazz);
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            responCallBack.onResponse(bean);
//                        }
//                    });
//                    responCallBack.onResponse(bean);
                    mHandler.post(new ResponseRunnable<Bean>(responCallBack, bean));
                } catch (Exception e) {
                    e.printStackTrace();//把错误信息直接输出
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //为毛要把错误信息handler，，因为假如没有网，那就崩了啊，，所以要提示错误的信息给用户啊，，可以打toast啊
//                            responCallBack.onError(e);
//
//                        }
//                    });
//                    responCallBack.onError(e);
                    mHandler.post(new ErrorRunnable<Bean>(responCallBack, e));

                }

            }
        });
    }


    //一个抽象类,,相当于基类
    abstract class HTTPRunnable<Bean> implements Runnable {
        protected ResponCallBack<Bean> mResponCallBack;

        public HTTPRunnable(ResponCallBack<Bean> responCallBack) {
            mResponCallBack = responCallBack;
        }
    }

    //把Runnable封装（继承抽象类）
    class ErrorRunnable<Bean> extends HTTPRunnable<Bean> {
        private Exception mException;

        public ErrorRunnable(ResponCallBack<Bean> responCallBack, Exception e) {
            super(responCallBack);

        }

        @Override
        public void run() {
            mResponCallBack.onError(mException);
        }
    }

    //把Runnable封装（继承抽象类）
    class ResponseRunnable<Bean> extends HTTPRunnable<Bean> {
        private Bean bean;

        public ResponseRunnable(ResponCallBack<Bean> responCallBack, Bean bean) {
            super(responCallBack);
            this.bean = bean;

        }

        @Override
        public void run() {
            mResponCallBack.onResponse(bean);
        }
    }

}
