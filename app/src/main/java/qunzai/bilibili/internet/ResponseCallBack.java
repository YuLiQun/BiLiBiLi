package qunzai.bilibili.internet;

/**
 * Created by XingMingDa on 16/11/28.
 * 网络请求的接口,请求成功和请求失败
 */

public interface ResponseCallBack <T>{
    //请求成功 直接返回 数据类
    void onResponse(T t);

    //请求失败,返回异常信息
    void onError(Exception exception);
}
