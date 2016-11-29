package qunzai.bilibili.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by QunZai on 16/11/26.
 */
public class RecommendAdapter  extends RecyclerView.Adapter{
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO 这里可以继续
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_recommend_rv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
