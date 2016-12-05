package qunzai.bilibili.classify.child;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;

/**
 * Created by QunZai on 16/12/3.
 */
public class ClassifyChildActivity extends BaseActivity{

    private TabLayout mTb;
    private ViewPager mVp;
    private Toolbar mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_classity_child;
    }

    @Override
    protected void initViews() {
        mTb = bindView(R.id.activity_classify_child_tb);
        mVp = bindView(R.id.activity_classify_child_vp);
        mToolbar = bindView(R.id.activity_classify_child_vp);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("ClassifyUrl");

        ClassifyChildAdapter adapter = new  ClassifyChildAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
        mTb.setupWithViewPager(mVp);


    }
}
