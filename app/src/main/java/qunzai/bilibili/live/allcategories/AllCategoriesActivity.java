package qunzai.bilibili.live.allcategories;

import android.support.v7.widget.RecyclerView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.utils.CircleImageView;

/**
 * Created by dllo on 16/11/24.
 */

public class AllCategoriesActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayout() {
        return R.layout.activity_all_categories;
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_all_categories_recycler_view);
    }

    @Override
    protected void initData() {
        AllCategoriesAdapter adapter = new AllCategoriesAdapter();

    }
}
