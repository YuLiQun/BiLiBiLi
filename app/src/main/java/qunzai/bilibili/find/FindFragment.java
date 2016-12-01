package qunzai.bilibili.find;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.base.HeaderAdapter;
import qunzai.bilibili.bean.FindTextBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.internet.UrlClass;
import qunzai.bilibili.utils.CustomView;
import qunzai.bilibili.utils.SingletonUtils;

/**
 * Created by QunZai on 16/11/22.
 */

public class FindFragment extends BaseFragment {

    private RecyclerView mRv;
    private View mSearchView;
    private View mContentView;
    private View mTextView;
    private TextView mTextTv;
    private CustomView mContentCV;

    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initViews() {


        mRv = bindView(R.id.fragment_find_rv);
        mSearchView = LayoutInflater.from(mContext).inflate(R.layout.fragment_find_search,null);
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.fragment_find_content,null);
        mContentCV = bindView(mContentView,R.id.fragment_find_custom_cv);



    }
    @Override
    protected void initData() {
        FindAdapter adapter = new FindAdapter();


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);

        initText();


        HeaderAdapter headerAdapter = new HeaderAdapter(adapter);
        headerAdapter.addHeadView(mSearchView);
        headerAdapter.addHeadView(mContentView);
        mRv.setAdapter(headerAdapter);
    }

    private void initText() {
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(UrlClass.URL_FIND_LABEL, FindTextBean.class, new ResponseCallBack<FindTextBean>() {

            private TextView mTextView;
            private ViewGroup.MarginLayoutParams mMarginLayoutParams;

            @Override
            public void onResponse(FindTextBean findTextBean) {
                int size = findTextBean.getList().size();

                for (int i = 0; i < size; i++) {
                    String keyword = findTextBean.getList().get(i).getKeyword();
                    View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_find_text,null);
                    mTextView = bindView(view, R.id.fragment_find_text_tv);


                    mTextView.setText(keyword);

                    mTextView.setBackgroundResource(R.drawable.find_text_back);

                    mMarginLayoutParams = new ViewGroup.MarginLayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    mMarginLayoutParams.setMargins(5,3,5,3);
                    mContentCV.addView(mTextView, mMarginLayoutParams);


                }
            }

            @Override
            public void onError(Exception exception) {

            }
        });


    }



}
