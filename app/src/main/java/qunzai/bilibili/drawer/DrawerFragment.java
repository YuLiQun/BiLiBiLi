package qunzai.bilibili.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import qunzai.bilibili.main.MainActivity;


/**
 * Created by QunZai on 16/11/24.
 */

public class DrawerFragment extends BaseFragment implements View.OnClickListener {

    private ListView mLv;
    private TextView mUsername;
    private ImageView mImgMoon,mImgSun;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

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
        mImgMoon = bindView(headView, R.id.item_drawer_header_moon_img);
        mImgSun = bindView(headView, R.id.item_drawer_header_sun_img);
        mImgMoon.setOnClickListener(this);
        mImgSun.setOnClickListener(this);

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


    @Override
    public void onClick(View view) {
        TypedValue attrTypedValue;
        switch (view.getId()) {
            case R.id.item_drawer_header_moon_img://点击变成夜间模式
                mImgMoon.setVisibility(View.INVISIBLE);
                mImgSun.setVisibility(View.VISIBLE);

                break;

            case R.id.item_drawer_header_sun_img://点击变成日间模式
                mImgSun.setVisibility(View.INVISIBLE);
                mImgMoon.setVisibility(View.VISIBLE);

                break;

        }
    }


}
