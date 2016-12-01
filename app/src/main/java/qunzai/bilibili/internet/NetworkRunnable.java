package qunzai.bilibili.internet;

/**
 * Created by dllo on 16/11/28.
 */

public abstract class NetworkRunnable<T> implements Runnable{

    protected ResponseCallBack<T> mResponseCallBack;

    protected NetworkRunnable(ResponseCallBack<T> responseCallBack) {
        mResponseCallBack = responseCallBack;
    }
}
