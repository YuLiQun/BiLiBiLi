package qunzai.bilibili.drawer.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobUser;
import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.bean.EventBusBean;

/**
 * Created by QunZai on 16/12/3.
 */
public class LoginAfterActivity extends BaseActivity implements View.OnClickListener {

    private TextView mBackTv;
    private TextView mUserNameTv,mExitTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_login_after;
    }

    @Override
    protected void initViews() {
        mBackTv = bindView(R.id.activity_login_after_back_tv);
        mUserNameTv = bindView(R.id.activity_login_after_tv);
        mExitTv = bindView(R.id.activity_login_after_exit_tv);
        mBackTv.setOnClickListener(this);
        mExitTv.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        SharedPreferences spName = getSharedPreferences("LoggingStatus",MODE_PRIVATE);
        String username1 = spName.getString("username", username);
        mUserNameTv.setText(username1);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_login_after_back_tv:
                onBackPressed();
                break;
            case R.id.activity_login_after_exit_tv:
                // 获取存储的数据
                BmobUser.logOut();
                SharedPreferences sp = getSharedPreferences("LoggingStatus", MODE_PRIVATE);
                SharedPreferences.Editor spET = sp.edit();
                spET.putString("username", "");
                spET.putBoolean("status", false);
                spET.clear();
                spET.commit();

                /*EventBus  发送,,退出登录就发送一个空的字符串*/
                EventBusBean eventBusBean = new EventBusBean();
                eventBusBean.setUsername("");
                EventBus.getDefault().postSticky(eventBusBean);

                finish();
                break;
        }

    }
}
