package qunzai.bilibili.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qunZai on 16/11/28.
 */

public class HeaderAdapter extends RecyclerView.Adapter {

    //内部的Adapter  就是我们之前写的没有头布局的Adapter
    private RecyclerView.Adapter innerAdapter;
    private static final int BASE_TYPE = 100000;
    private SparseArray<View> headViews;

    public HeaderAdapter(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
        headViews = new SparseArray<>();
    }

    //添加头布局
    public void addHeadView(View headView) {
        headViews.put(BASE_TYPE + headViews.size(),
                headView);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //头布局
        if (position < headViews.size()) {
            return BASE_TYPE + position;
        } else {
            //代表position对应的是 里面Adapter的数据
            return innerAdapter.getItemViewType(position - headViews.size());
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (headViews.get(viewType) != null) {
            holder = CommonViewHolder.getHeadViewHolder(headViews.get(viewType));
        } else {
            holder = innerAdapter.createViewHolder(parent, viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= headViews.size()) {
            innerAdapter.onBindViewHolder(holder, position - headViews.size());
        }
    }

    @Override
    public int getItemCount() {
        //总共的行布局个数是 头布局个数 + 里面Adapter数据的个数和
        return headViews.size() + innerAdapter.getItemCount();
    }

        @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {  //判断左边是不是右边的子类或实例(左边能不能强转成右边)
            //如果是列表形式的LayoutManager
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            //列数
            final int spanCount = gridLayoutManager.getSpanCount();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {  //如果是头布局就跨咧
                    if (position < headViews.size()){
                        //是头布局
                        return spanCount;
                    }
                    return 1;  //抽象方法默认返回值0
                }
            });
        }

    }

}
