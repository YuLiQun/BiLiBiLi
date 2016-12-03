package qunzai.bilibili.live.allcategories.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BilibiliApp;
import qunzai.bilibili.bean.AllCategoriesBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.live.allcategories.presenter.AllCategoriesAdapter;
import qunzai.bilibili.live.allcategories.presenter.AllCategoriesPresenterImpl;
import qunzai.bilibili.live.allcategories.SpaceItemDecoration;

import static qunzai.bilibili.internet.UrlClass.URL_ALL_CATEGORIES;

/**
 * Created by XingMingDa on 16/11/24.
 * AllCategoriesActivity
 */

public class AllCategoriesActivity extends BaseActivity implements AllCategoriesView,View.OnClickListener {

    private AllCategoriesPresenterImpl mPresenter;

    private RecyclerView recyclerView;
    private AllCategoriesAdapter adapter;
    private View view;
    private ImageView backIv;
    private TextView titleTv;
    private ImageView searchIv;


    @Override
    protected int getLayout() {
        return R.layout.activity_all_categories;
    }

    @Override
    protected void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_all_categories_recycler_view);
        view =findViewById(R.id.activity_all_categories_title);
        backIv = (ImageView) view.findViewById(R.id.layout_title_back_iv);
        titleTv = (TextView) view.findViewById(R.id.layout_title_tv);

        searchIv = (ImageView) view.findViewById(R.id.layout_title_search_iv);
    }

    @Override
    protected void initData() {
        titleTv.setText(R.string.activity_all_categories_title);
        backIv.setImageResource(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
        backIv.setOnClickListener(this);
        adapter = new AllCategoriesAdapter();
        RecyclerView.LayoutManager manager = new GridLayoutManager(BilibiliApp.getContext(),3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration());
        recyclerView.setAdapter(adapter);
        mPresenter = new AllCategoriesPresenterImpl(this);
        mPresenter.getAllCategories(URL_ALL_CATEGORIES,AllCategoriesBean.class);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAllCategoriesRequest(AllCategoriesBean allCategoriesBean) {
        adapter.setAllCategoriesBean(allCategoriesBean);
    }
}
