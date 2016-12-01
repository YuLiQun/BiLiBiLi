package qunzai.bilibili.drawer.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;

/**
 * Created by QunZai on 16/11/25.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBanckImg;
    private EditText mPhoneEt;
    private EditText mPswEt;
    private TextView mLogonTV;

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

        mBanckImg.setOnClickListener(this);
        mPhoneEt.setOnClickListener(this);
        mPswEt.setOnClickListener(this);
        mLogonTV.setOnClickListener(this);

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
            case R.id.activity_login_logon_tv:
                Intent intent = new Intent(LoginActivity.this,LogonActivity.class);
                startActivity(intent);
                break;
        }
    }
}
