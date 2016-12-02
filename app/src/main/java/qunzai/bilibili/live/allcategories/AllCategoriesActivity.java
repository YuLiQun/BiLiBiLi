package qunzai.bilibili.live.allcategories;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BilibiliApp;
import qunzai.bilibili.bean.AllCategoriesBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;

import static qunzai.bilibili.internet.UrlClass.URL_ALL_CATEGORIES;

/**
 * Created by XingMingDa on 16/11/24.
 * AllCategoriesActivity
 */

public class AllCategoriesActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AllCategoriesAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_all_categories;
    }

    @Override
    protected void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_all_categories_recycler_view);
    }

    @Override
    protected void initData() {
        adapter = new AllCategoriesAdapter();
        getAllCategoriesRequest();
        RecyclerView.LayoutManager manager = new GridLayoutManager(BilibiliApp.getContext(),3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new SpaceItemDecoration());
        recyclerView.setAdapter(adapter);
    }

    private void getAllCategoriesRequest() {
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(URL_ALL_CATEGORIES, AllCategoriesBean.class, new ResponseCallBack<AllCategoriesBean>() {
            @Override
            public void onResponse(AllCategoriesBean allCategoriesBean) {
                adapter.setAllCategoriesBean(allCategoriesBean);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }

}
