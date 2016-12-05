package qunzai.bilibili.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.bean.EventBusBean;
import qunzai.bilibili.drawer.login.LoginActivity;
import qunzai.bilibili.drawer.login.LoginAfterActivity;

/**
 * Created by QunZai on 16/11/24.
 */

public class DrawerFragment extends BaseFragment {

    private ListView mLv;
    private TextView mUsername;

    @Override
    protected int getLayout() {
        return R.layout.fragment_drawer;
    }

    @Override
    protected void initViews() {
        /*注册EventBus*/
        EventBus.getDefault().register(this);
        mLv = bindView(R.id.fragment_drawer_lv);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.item_drawer_header,null);
        mLv.addHeaderView(headView);
        mUsername = bindView(headView, R.id.item_drawer_header_username_tv);

    }

    @Override
    protected void initData() {
        DrawerAdapter adapter = new DrawerAdapter();
        mLv.setAdapter(adapter);
        mLvClick();
    }

    private void mLvClick() {
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:

                        SharedPreferences getSp = mContext.getSharedPreferences("LoggingStatus", Context.MODE_PRIVATE);
                        boolean isStatus = getSp.getBoolean("status", false);
                        String username = getSp.getString("username", "");

                        if (isStatus == false) {//如果没有登录
                            Intent intent = new Intent(mContext,LoginActivity.class);
                            startActivity(intent);
                        }else {//如果登录以后

                            Intent intentAfter = new Intent(mContext, LoginAfterActivity.class);
                            startActivity(intentAfter);
                        }

                        break;
                }
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)//先发送后注册,,粘性,,,将发送和接受绑定在一起
    public void getTextEvent(EventBusBean eventBusBean){
        String username = eventBusBean.getUsername();
        mUsername.setText(username);
    }


}
