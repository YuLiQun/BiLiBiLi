package qunzai.bilibili.internet;

/**
 * Created by qunzai on 16/11/26.
 * 接口
 */

public interface ResponCallBack<Bean> {
    void onResponse(Bean bean);//请求成功，直接返回数据类
    void onError(Exception e);//请求失败，直接返回异常信息
}
