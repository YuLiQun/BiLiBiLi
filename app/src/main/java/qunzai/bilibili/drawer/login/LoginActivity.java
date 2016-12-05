package qunzai.bilibili.drawer.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BilibiliUser;
import qunzai.bilibili.bean.EventBusBean;
import qunzai.bilibili.drawer.logon.LogonActivity;

/**
 * Created by QunZai on 16/11/25.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBanckImg;
    private EditText mPhoneEt;
    private EditText mPswEt;
    private TextView mLogonTV, mLoginTV;
    private String mPhone;
    private String mPsw;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        mBanckImg = bindView(R.id.include_login_back_img);
        mPhoneEt = bindView(R.id.activity_login_phone_et);
        mPswEt = bindView(R.id.activity_login_psw_et);
        mLogonTV = bindView(R.id.activity_login_logon_tv);
        mLoginTV = bindView(R.id.activity_login_login_tv);

        mBanckImg.setOnClickListener(this);
        mPhoneEt.setOnClickListener(this);
        mPswEt.setOnClickListener(this);
        mLogonTV.setOnClickListener(this);
        mLoginTV.setOnClickListener(this);

    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.include_login_back_img:
                onBackPressed();
                break;
            case R.id.activity_login_logon_tv://注册
                final Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_login_login_tv://登录

                if (mPhoneEt.getText().toString().length() != 0 && mPswEt.getText().toString().length() != 0) {

                    BilibiliUser myUser = new BilibiliUser();
                    myUser.setUsername(mPhoneEt.getText().toString());
                    myUser.setPassword(mPswEt.getText().toString());
                    myUser.login(new SaveListener<BilibiliUser>() {
                        @Override
                        public void done(BilibiliUser myUser, BmobException e) {

                            if (e == null) {
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                                // 获取存储的数据
                                SharedPreferences sp = getSharedPreferences("LoggingStatus", MODE_PRIVATE);
                                SharedPreferences.Editor spET = sp.edit();
                                spET.putString("username", mPhoneEt.getText().toString());
                                spET.putBoolean("status", true);
                                spET.commit();

                                /*EventBus*/
                                EventBusBean event = new EventBusBean();
                                event.setUsername(mPhoneEt.getText().toString());
                                //粘性发送
                                EventBus.getDefault().postSticky(event);


                                Intent intentAfter = new Intent(LoginActivity.this, LoginAfterActivity.class);
                                intentAfter.putExtra("username", mPhoneEt.getText().toString());
                                startActivity(intentAfter);
                                finish();

                            } else {
                                Log.d("MainActivity", e.getMessage());
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this, "请输入账号/密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
