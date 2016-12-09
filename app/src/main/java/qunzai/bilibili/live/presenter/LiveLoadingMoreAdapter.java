package qunzai.bilibili.live.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.base.MultiTypeBaseAdapter;
import qunzai.bilibili.bean.LiveContentBean;

/**
 * Created by XingMingDa on 16/12/8.
 *
 */

public class LiveLoadingMoreAdapter extends MultiTypeBaseAdapter<LiveContentBean> {


    public LiveLoadingMoreAdapter(Context context, List datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
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
            return R.layout.item_live_home;
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);


    }
}
