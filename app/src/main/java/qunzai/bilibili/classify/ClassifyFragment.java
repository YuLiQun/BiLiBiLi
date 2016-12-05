package qunzai.bilibili.classify;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.bean.ClassifyTitleBean;
import qunzai.bilibili.classify.child.ClassifyChildActivity;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.internet.UrlClass;

/**
 * Created by QunZai on 16/11/23.
 * 匪类,,,分区
 */

public class ClassifyFragment extends BaseFragment {


    private RecyclerView mRv;
    private ArrayList<String> mArrayListId;
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


        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(UrlClass.URL_LIVE_TAAG, ClassifyTitleBean.class, new ResponseCallBack<ClassifyTitleBean>() {
            @Override
            public void onResponse(ClassifyTitleBean classifyTitleBean) {
                mArrayListId = new ArrayList<String>();
                int size = classifyTitleBean.getData().size();
                for (int i = 0; i < size; i++) {
                    int id = classifyTitleBean.getData().get(i).getId();

                    mArrayListId.add(String.valueOf(id));
                }

                mAdapter = new ClassifyAdapter(mArrayListId);
                mRv.setAdapter(mAdapter);

                GridLayoutManager layoutManager = new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);
                mRv.setLayoutManager(layoutManager);

                initClick();

            }

            @Override
            public void onError(Exception exception) {

            }
        });



    }

    private void initClick() {
        mAdapter.setOnRvClickListener(new OnRvClickListener() {
            @Override
            public void onClick(int position) {
                String id = mArrayListId.get(position);

                String url = UrlClass.URL_ALL_TYPE(Integer.valueOf(id));
                Intent intent = new Intent(mContext,ClassifyChildActivity.class);
                intent.putExtra("ClassifyUrl",url);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
    }


}
