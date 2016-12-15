package qunzai.bilibili.communication;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.classify.OnRvClickListener;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.internet.UrlClass;

/**
 * Created by ZhangRui on 16/11/22.
 * 客服，通信
 */

public class CommunicationFragment extends BaseFragment {

    private RecyclerView mLv;
    private TextView mComm;
-
    @Override
    protected void initData() {


    }

    @Override
    protected void initViews() {
        mLv = bindView(R.id.fragment_communication_lv);
        mComm = bindView(R.id.fragment_comm_tv);
        CommunicationAdapter communicationAdapter = new CommunicationAdapter();
        mLv.setAdapter(communicationAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mLv.setLayoutManager(manager);
        communicationAdapter.setOnRvClickListener(new OnRvClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), TuringActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_communication;
    }
}
