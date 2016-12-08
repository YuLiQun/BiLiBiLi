package qunzai.bilibili.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.support.v4.view.ViewPager;


import java.nio.Buffer;
import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.classify.ClassifyFragment;
import qunzai.bilibili.communication.CommunicationFragment;
import qunzai.bilibili.concern.ConcernFragment;
import qunzai.bilibili.drawer.DrawerFragment;
import qunzai.bilibili.find.FindFragment;
import qunzai.bilibili.live.LiveFragment;
import qunzai.bilibili.recommend.RecommendFragment;

public class MainActivity extends BaseActivity {

    private TabLayout tb;
    private ViewPager vp;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private FragmentManager mFragmentManager;
    private DrawerFragment mDrawerFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initViews() {
        tb = bindView(R.id.activity_main_tb);
        vp = bindView(R.id.activity_main_vp);
        mToolbar = bindView(R.id.activity_main_toolbar);
        mFrameLayout = bindView(R.id.fragment_drawer_fl);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void initData() {

        mDrawerFragment = new DrawerFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fragment_drawer_fl, mDrawerFragment).commit();

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new CommunicationFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new ConcernFragment());
        fragments.add(new FindFragment());
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);


    }



}
