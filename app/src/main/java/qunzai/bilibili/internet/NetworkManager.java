package qunzai.bilibili.internet;

import java.util.HashMap;

import okhttp3.Request;

/**
 * Created by XingMingDa on 16/11/22.
 * NetworkManager
 */

public abstract class NetworkManager {
        protected abstract <T> void get(String url,Class<T> clazz, ResponseCallBack<T> responseCallBack);
        protected abstract <T> void post(String url,Class<T> clazz, ResponseCallBack<T> responseCallBack,HashMap<String, String> body);
        protected abstract <T> void sendHttpRequest(Request request, Class<T> clazz, ResponseCallBack<T> responseCallBack);
}
