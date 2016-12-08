package qunzai.bilibili.find;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.bean.FindTextBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.internet.UrlClass;

/**
 * Created by QunZai on 16/12/8.
 */
public class FindContentActivity extends BaseActivity{

    private TabLayout mTb;
    private ViewPager mVp;
    private String mSearch;

    @Override
    protected int getLayout() {
        return R.layout.activity_find_content;
    }

    @Override
    protected void initViews() {

        mTb = bindView(R.id.activity_find_content_tb);
        mVp = bindView(R.id.activity_find_content_vp);

    }

    @Override
    protected void initData() {
        Intent intent =getIntent();
        mSearch = intent.getStringExtra("search");

        ContentAdapter adapter = new ContentAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mTb.setupWithViewPager(mVp);


//        initContent();
    }

//    private void initContent() {
//        OkHttpManager okHttpManager = new OkHttpManager();
//        okHttpManager.get(UrlClass.SEARCH_DETAIL(1,mSearch,), FindTextBean.class, new ResponseCallBack<FindTextBean>() {
//
//            private TextView mTextView;
//            private ViewGroup.MarginLayoutParams mMarginLayoutParams;
//
//            @Override
//            public void onResponse(FindTextBean findTextBean) {
//                int size = findTextBean.getList().size();
//
//                for (int i = 0; i < size; i++) {
//                    String keyword = findTextBean.getList().get(i).getKeyword();
//                    View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_find_text, null);
//                    mTextView = bindView(view, R.id.fragment_find_text_tv);
//
//
//                    mTextView.setText(keyword);
//
//                    mTextView.setBackgroundResource(R.drawable.find_text_back);
//
//                    mMarginLayoutParams = new ViewGroup.MarginLayoutParams(
//                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    mMarginLayoutParams.setMargins(5, 3, 5, 3);
//                    mContentCV.addView(mTextView, mMarginLayoutParams);
//
//
//                }
//            }
//
//            @Override
//            public void onError(Exception exception) {
//
//            }
//        });}

}
