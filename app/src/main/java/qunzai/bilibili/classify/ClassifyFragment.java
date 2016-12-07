package qunzai.bilibili.classify;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.bean.ClassifyTitleBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.internet.UrlClass;

/**
 * Created by QunZai on 16/11/23.
 * 匪类,,,分区
 */

public class ClassifyFragment extends BaseFragment {


    private RecyclerView mRv;

    private ClassifyAdapter mAdapter;

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

        mAdapter = new ClassifyAdapter();
        mRv.setAdapter(mAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);



    }


}
