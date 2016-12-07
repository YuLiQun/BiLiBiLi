package qunzai.bilibili.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;

import qunzai.bilibili.base.BaseFragment;

/**
 * Created by QunZai on 16/11/23.
 */
public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private String title[] = {"直播", "游戏", "客服","分区", "关注", "发现"};


    public MainAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
