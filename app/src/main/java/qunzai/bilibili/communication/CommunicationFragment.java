package qunzai.bilibili.communication;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;

/**
 * Created by ZhangRui on 16/11/22.
 * 客服，通信
 */

public class CommunicationFragment extends BaseFragment {

    private ListView mLv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        mLv = bindView(R.id.fragment_communication_lv);
        CommunicationAdapter communicationAdapter = new CommunicationAdapter(getContext());
        mLv.setAdapter(communicationAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
