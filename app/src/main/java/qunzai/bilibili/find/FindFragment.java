package qunzai.bilibili.find;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.base.HeaderAdapter;

/**
 * Created by QunZai on 16/11/22.
 */

public class FindFragment extends BaseFragment {

    private RecyclerView mRv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initViews() {
        mRv = bindView(R.id.fragment_find_rv);

    }
    @Override
    protected void initData() {
        FindAdapter adapter = new FindAdapter();


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);

        View searchView = LayoutInflater.from(mContext).inflate(R.layout.fragment_find_search,null);
        HeaderAdapter headerAdapter = new HeaderAdapter(adapter);
        headerAdapter.addHeadView(searchView);

        mRv.setAdapter(headerAdapter);
    }




}
