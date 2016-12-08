package qunzai.bilibili.communication;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.classify.OnRvClickListener;

/**
 * Created by ZhangRui on 16/11/22.
 * 客服，通信
 */

public class CommunicationFragment extends BaseFragment {

    private RecyclerView mLv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        mLv = bindView(R.id.fragment_communication_lv);
        CommunicationAdapter communicationAdapter = new CommunicationAdapter();
        mLv.setAdapter(communicationAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mLv.setLayoutManager(manager);
        communicationAdapter.setOnRvClickListener(new OnRvClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(),TuringActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_communication;
    }
}
