package qunzai.bilibili.concern;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.drawer.login.LoginActivity;
import qunzai.bilibili.drawer.login.LoginAfterActivity;

/**
 * Created by QunZai on 16/11/22.
 * 关注
 */

public class ConcernFragment extends BaseFragment {

    private Button mLoginBtn;

    @Override
    protected void initData() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

            }
        });


//        initLogin();
    }

    private void initLogin() {

    }

    @Override
    protected void initViews() {
        mLoginBtn = bindView(R.id.fragment_concern_login_btn);
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_concern;
    }


}
