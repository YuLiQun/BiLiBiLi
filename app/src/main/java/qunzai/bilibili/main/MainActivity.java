package qunzai.bilibili.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.classify.ClassifyFragment;
import qunzai.bilibili.communication.CommunicationFragment;
import qunzai.bilibili.concern.ConcernFragment;
import qunzai.bilibili.find.FindFragment;
import qunzai.bilibili.live.view.LiveFragment;
import qunzai.bilibili.recommend.RecommendFragment;

public class MainActivity extends BaseActivity {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initViews() {
        tb = bindView(R.id.activity_main_tb);
        vp = bindView(R.id.activity_main_vp);
    }

    @Override
    protected void initData() {
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
