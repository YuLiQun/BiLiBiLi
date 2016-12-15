package qunzai.bilibili.live.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.base.MultiTypeBaseAdapter;
import qunzai.bilibili.bean.LiveContentBean;
import qunzai.bilibili.live.allcategories.presenter.CategoriesAdapter;

/**
 * Created by XingMingDa on 16/12/8.
 *
 */

public class LiveLoadingMoreAdapter extends MultiTypeBaseAdapter<LiveContentBean.DataBean.PartitionsBean.LivesBean> {

    public LiveLoadingMoreAdapter(Context context, List datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(CommonViewHolder holder, LiveContentBean.DataBean.PartitionsBean.LivesBean data, int viewType) {
        holder.setImage(R.id.item_categories_cover_iv,data.getCover().getSrc()
                ,data.getCover().getWidth()
                ,data.getCover().getHeight())
                .setText(R.id.item_categories_title_tv,data.getTitle())
                .setText(R.id.item_categories_name_tv,data.getOwner().getName())
                .setText(R.id.item_categories_online_tv,String.valueOf(data.getOnline()))
                .setImage(R.id.item_categories_online_iv,R.mipmap.ic_watching);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_categories;
    }

    @Override
    protected int getViewType(int position, LiveContentBean.DataBean.PartitionsBean.LivesBean data) {
        return 0;
    }
}
