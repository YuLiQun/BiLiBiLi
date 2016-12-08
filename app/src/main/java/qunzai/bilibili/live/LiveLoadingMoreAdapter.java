package qunzai.bilibili.live;

import android.content.Context;

import java.util.List;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.base.MultiTypeBaseAdapter;
import qunzai.bilibili.bean.LiveContentBean;
import qunzai.bilibili.bean.LiveHeadBean;

/**
 * Created by XingMingDa on 16/12/8.
 *
 */

public class LiveLoadingMoreAdapter extends MultiTypeBaseAdapter<LiveContentBean> {

    private LiveHeadBean mLiveHeadBean;

    public LiveLoadingMoreAdapter(Context context, List datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    public void setLiveHeadBean(LiveHeadBean liveHeadBean) {
        mLiveHeadBean = liveHeadBean;
    }

    @Override
    protected void convert(CommonViewHolder holder, LiveContentBean data, int viewType) {
        if (viewType == 0) {

        } else {

        }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.item_live_head;
        }
        return R.layout.item_live;
    }

    @Override
    protected int getViewType(int position, LiveContentBean data) {
        if (data == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
