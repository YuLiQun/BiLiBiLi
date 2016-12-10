package qunzai.bilibili.live.allcategories.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BilibiliApp;
import qunzai.bilibili.bean.CategoriesBean;
import qunzai.bilibili.live.allcategories.presenter.CategoriesAdapter;
import qunzai.bilibili.live.allcategories.presenter.CategoriesPresenterImpl;
import qunzai.bilibili.player.IjkPlayerActivity;

/**
 * Created by XingMingDa on 16/12/6.
 * 所有分类复用Activity
 */

public class CategoriesActivity extends BaseActivity implements CategoriesView, View.OnClickListener, OnClickLiveEnter {

    private CategoriesPresenterImpl mPresenter;

    private View view;
    private ImageView backIv;
    private TextView titleTv;
    private ImageView searchIv;
    private RecyclerView mRecyclerView;
    private CategoriesAdapter mAdapter;
    private Bundle mBundle;

    @Override
    protected int getLayout() {
        return R.layout.activity_categories;
    }

    @Override
    protected void initViews() {
        view = findViewById(R.id.activity_all_categories_title);
        backIv = (ImageView) view.findViewById(R.id.layout_title_back_iv);
        titleTv = (TextView) view.findViewById(R.id.layout_title_tv);
        searchIv = (ImageView) view.findViewById(R.id.layout_title_search_iv);
        mRecyclerView = bindView(R.id.activity_categories_recycler_view);
    }

    @Override
    protected void initData() {
        backIv.setImageResource(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
        backIv.setOnClickListener(this);
        mAdapter = new CategoriesAdapter();
        mAdapter.setOnClickLiveEnter(this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(BilibiliApp.getContext(),2);
        mRecyclerView.setLayoutManager(manager);
        getCategoriesInfo();
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new CategoriesPresenterImpl(this);
        mPresenter.getCategories(mBundle.getString("CategoriesUrl"),CategoriesBean.class);
    }

    private void getCategoriesInfo() {
        mBundle = getIntent().getBundleExtra("Categories");
        titleTv.setText(mBundle.getString("CategoriesName"));
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void getCategoriesRequest(CategoriesBean categoriesBean) {
        mAdapter.setCategoriesBean(categoriesBean);
    }

    @Override
    public void onClick(CategoriesBean bean, int position) {
        Intent intent = new Intent(this,IjkPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Data",bean);
        bundle.putInt("Position",position);
        intent.putExtra("IjkPlayerData",bundle);
        startActivity(intent);
    }
}
