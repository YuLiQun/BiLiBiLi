package qunzai.bilibili.live.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.base.BilibiliApp;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.base.Interfaces.OnMultiTypeItemClickListeners;
import qunzai.bilibili.bean.LiveContentBean;
import qunzai.bilibili.live.allcategories.view.AllCategoriesActivity;
import qunzai.bilibili.live.presenter.ImageLoopAdapter;
import qunzai.bilibili.live.presenter.LiveLoadingMoreAdapter;
import qunzai.bilibili.live.presenter.LivePresenterImpl;

import static qunzai.bilibili.internet.UrlClass.URL_LIVE;

/**
 * Created by XingMingDa on 16/11/22.
 * 直播
 */

public class LiveFragment extends BaseFragment implements LiveView, View.OnClickListener {

    private LivePresenterImpl mLivePresenter;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LiveLoadingMoreAdapter mAdapter;
    private RollPagerView mRollPagerView;
    private LinearLayout mAllCategoriseLl;
    private ImageLoopAdapter mLoopAdapter;
    private LinearLayout mConcernAnchorLl;
    private int mContentNumber;
    private LiveContentBean mLiveContentBean;

    @Override
    protected int getLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = bindView(R.id.fragment_live_swipe);
        mRecyclerView = bindView(R.id.fragment_live_recycler_view);
        View view = bindView(R.id.fragment_live_home);
        mRollPagerView = bindView(view, R.id.item_live_head_roll_pager_view);
        mAllCategoriseLl = bindView(view, R.id.item_live_head_all_categorise_ll);
        mConcernAnchorLl = bindView(view, R.id.item_live_head_concern_anchor_ll);
        mAllCategoriseLl.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorTitleBackground));
        GridLayoutManager manager = new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new LiveLoadingMoreAdapter(getContext(),null,true);
        mLoopAdapter = new ImageLoopAdapter(mRollPagerView);

        mLivePresenter = new LivePresenterImpl(this);

        mAdapter.setOnMultiTypeItemClickListener(new OnMultiTypeItemClickListeners<LiveContentBean.DataBean.PartitionsBean.LivesBean>() {
            @Override
            public void onItemClick(CommonViewHolder viewHolder, LiveContentBean.DataBean.PartitionsBean.LivesBean data, int position, int viewType) {

            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mContentNumber > mLiveContentBean.getData().getPartitions().size()){
                    mAdapter.setLoadEndView(R.layout.load_end);
                }else {
                    mContentNumber++;
                    mAdapter.setLoadMoreData(mLiveContentBean.getData().getPartitions().get(mContentNumber).getLives());
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mLivePresenter.getLiveContent(URL_LIVE,LiveContentBean.class);
    }

    @Override
    public void getLiveContentRequest(LiveContentBean liveContentBean) {

        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < liveContentBean.getData().getBanner().size(); i++) {
            imageUrls.add(liveContentBean.getData().getBanner().get(i).getImg());
        }
        mLoopAdapter.setImageUrls(imageUrls);
        mRollPagerView.setAdapter(mLoopAdapter);

        mContentNumber = 0;
        mLiveContentBean = liveContentBean;
        mAdapter.setInitData(mLiveContentBean.getData().getPartitions().get(mContentNumber).getLives());
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.setLoadingView(R.layout.load_loading);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(BilibiliApp.getContext(), AllCategoriesActivity.class);
        startActivity(intent);
    }
}
