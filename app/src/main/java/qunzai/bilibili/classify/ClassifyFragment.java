package qunzai.bilibili.classify;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;

/**
 * Created by QunZai on 16/11/23.
 * 匪类,,,分区
 */

public class ClassifyFragment extends BaseFragment {


    private RecyclerView mRv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initViews() {
        mRv = bindView(R.id.fragment_classify_rv);

    }

    @Override
    protected void initData() {
        ClassifyAdapter adapter = new ClassifyAdapter();
        mRv.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);
        mRv.setLayoutManager(layoutManager);
    }




}
