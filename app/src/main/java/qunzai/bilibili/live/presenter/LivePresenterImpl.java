package qunzai.bilibili.live.presenter;

import qunzai.bilibili.bean.LiveContentBean;
import qunzai.bilibili.live.modle.LiveModelImpl;
import qunzai.bilibili.live.view.LiveFragment;

/**
 * Created by XingMingDa on 16/12/9.
 */

public class LivePresenterImpl implements LivePresenter,OnGetLiveContentListener{

    private LiveFragment mLiveFragment;
    private LiveModelImpl mLiveModel;

    public LivePresenterImpl(LiveFragment liveFragment) {
        mLiveFragment = liveFragment;
        this.mLiveModel = new LiveModelImpl();
    }

    @Override
    public void getLiveContent(String url, Class clazz) {
        mLiveModel.getLiveContentRequest(url,clazz,this);
    }

    @Override
    public void onSuccess(LiveContentBean bean) {
        mLiveFragment.getLiveContentRequest(bean);
    }
}
