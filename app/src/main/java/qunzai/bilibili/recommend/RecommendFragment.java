package qunzai.bilibili.recommend;

import android.support.v7.widget.RecyclerView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;

/**
 * Created by QunZai on 16/11/22.
 * 推荐页
 */

public class RecommendFragment extends BaseFragment {

    private RecyclerView mRv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        mRv = bindView(R.id.fragment_recommend_rv);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }
}
