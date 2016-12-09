package qunzai.bilibili.live;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.base.Interfaces.OnLoadMoreListener;
import qunzai.bilibili.base.Interfaces.OnMultiTypeItemClickListeners;
import qunzai.bilibili.bean.LiveContentBean;

import static qunzai.bilibili.base.BilibiliApp.getContext;

/**
 * Created by XingMingDa on 16/11/22.
 * 直播
 */

public class LiveFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LiveLoadingMoreAdapter mAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = bindView(R.id.fragment_live_swipe);
        mRecyclerView = bindView(R.id.fragment_live_recycler_view);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorTitleBackground));
        GridLayoutManager manager = new GridLayoutManager(getContext(),6, GridLayoutManager.VERTICAL,false);
        mAdapter = new LiveLoadingMoreAdapter(getContext(),null,true);
        mAdapter.setOnMultiTypeItemClickListener(new OnMultiTypeItemClickListeners<LiveContentBean>() {
            @Override
            public void onItemClick(CommonViewHolder viewHolder, LiveContentBean data, int position, int viewType) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadData();
            }
        });
    }

    private void loadData() {
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_live;
    }
}
