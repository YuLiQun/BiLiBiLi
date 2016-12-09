package qunzai.bilibili.live.modle;


import qunzai.bilibili.live.presenter.OnGetLiveContentListener;

/**
 * Created by XingMingDa on 16/12/9.
 */

public interface LiveModel {
    void getLiveContentRequest(String url, Class clazz, OnGetLiveContentListener listener);
}
