package qunzai.bilibili.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import android.widget.ImageView;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.bean.EventBusBean;
import qunzai.bilibili.classify.ClassifyFragment;
import qunzai.bilibili.communication.CommunicationFragment;
import qunzai.bilibili.concern.ConcernFragment;
import qunzai.bilibili.drawer.DrawerFragment;
import qunzai.bilibili.find.FindFragment;
import qunzai.bilibili.game.PhonePianoActivity;
import qunzai.bilibili.live.view.LiveFragment;


public class MainActivity extends BaseActivity {

    private TabLayout tb;
    private ViewPager vp;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayout;
    private FragmentManager mFragmentManager;
    private DrawerFragment mDrawerFragment;
    private TextView mUsername;
    private ImageView mGameIma;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initViews() {
        /*注册EventBus*/
        EventBus.getDefault().register(this);
        tb = bindView(R.id.activity_main_tb);
        vp = bindView(R.id.activity_main_vp);
        mToolbar = bindView(R.id.activity_main_toolbar);
        mFrameLayout = bindView(R.id.fragment_drawer_fl);
        setSupportActionBar(mToolbar);
        mUsername = bindView(R.id.include_username_tv);
        mGameIma = bindView(R.id.include_game_img);
    }

    @Override
    protected void initData() {

        mDrawerFragment = new DrawerFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fragment_drawer_fl, mDrawerFragment).commit();

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new CommunicationFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new ConcernFragment());
        fragments.add(new FindFragment());
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
        mGameIma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhonePianoActivity.class);
                startActivity(intent);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)//先发送后注册,,粘性,,,将发送和接受绑定在一起
    public void getTextEvent(EventBusBean eventBusBean){
        String username = eventBusBean.getUsername();
        if (username.length() != 0){
            mUsername.setText(username);
        }else {
            mUsername.setText("未登录");
        }

    }

}
