package qunzai.bilibili.live.modle;

import qunzai.bilibili.bean.LiveContentBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.live.presenter.OnGetLiveContentListener;


/**
 * Created by XingMingDa on 16/12/9.
 */

public class LiveModelImpl implements LiveModel{
    @Override
    public void getLiveContentRequest(String url, Class clazz, final OnGetLiveContentListener listener) {
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(url, clazz, new ResponseCallBack<LiveContentBean>() {
            @Override
            public void onResponse(LiveContentBean liveContentBean) {
                listener.onSuccess(liveContentBean);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }
}
