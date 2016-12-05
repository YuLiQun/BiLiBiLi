package qunzai.bilibili.drawer.logon;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.base.BilibiliUser;

/**
 * Created by QunZai on 16/12/1.
 */
public class LogonActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBackImg;
    private TextView mTitleTv;
    private TextView mLittleTitleTv;
    private EditText mPswEt,codeEt;
    private Button mLogonBtn, mGetCodeBtn;
    private int i = 30;
    private EditText mPhoneEt;
    private String mPhone;
    private String mCode;


    @Override
    protected int getLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_logon;
    }

    @Override
    protected void initViews() {

        mBackImg = bindView(R.id.include_login_back_img);
        mTitleTv = bindView(R.id.include_login_title_tv);
        mPhoneEt = bindView(R.id.activity_logon_phone_et);
        mPswEt = bindView(R.id.activity_logon_psw_et);
        mLittleTitleTv = bindView(R.id.include_login_little_title_tv);
        codeEt = bindView(R.id.activity_logon_code_et);//验证码
        mLogonBtn = bindView(R.id.activity_logon_visibility_btn);
        mGetCodeBtn = bindView(R.id.activity_logon_btn);

        mBackImg.setOnClickListener(this);
        mLogonBtn.setOnClickListener(this);
        mGetCodeBtn.setOnClickListener(this);
        mTitleTv.setText("注册账号");
        mLittleTitleTv.setVisibility(View.GONE);


    }

    @Override
    protected void initData() {

        initHandler();
    }

    private void initHandler() {

        EventHandler eventHandler = new EventHandler() {

            @Override

            public void afterEvent(int event, int result, Object data) {

                Message message = new Message();

                message.arg1 = event;

                message.arg2 = result;

                message.obj = data;

                handler.sendMessage(message);

            }

        };
        //注册回调监听接口

        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View view) {
        mCode = codeEt.getText().toString();
        switch (view.getId()) {
            case R.id.include_login_back_img:
                onBackPressed();
                break;
            case R.id.activity_logon_btn://获取验证码

                mPhone = mPhoneEt.getText().toString();
                if (judgePhoneNum(mPhone)) {
                    SMSSDK.getVerificationCode("86", mPhone);
                    mGetCodeBtn.setClickable(false);
                    mGetCodeBtn.setText("重新发送(" + i + ")");
                    new Thread(new Runnable() {

                        @Override

                        public void run() {
                            for (; i > 0; i--) {
                                handler.sendEmptyMessage(1);
                                if (i <= 0) {
                                    break;
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            handler.sendEmptyMessage(2);

                        }

                    }).start();

                    break;

                } else {

                }
                break;

            case R.id.activity_logon_visibility_btn://注册

                if(mCode.length() == 4 ){
                    //将收到的验证码和手机号提交再次核对
                    SMSSDK.submitVerificationCode("86", mPhone, mCode);
                    //        5.创建用户
                    BilibiliUser bmobUser = new BilibiliUser();
                    bmobUser.setUsername(mPhoneEt.getText().toString());//用户名
                    bmobUser.setPassword(mPswEt.getText().toString());//密码
                    bmobUser.signUp(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null){
                                Toast.makeText(LogonActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
//                                Toast.makeText(LogonActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("qqq", e.getMessage());//组册失败的原因

                            }
                        }
                    });//判断注册是否成功
                }else if (mCode.length() == 0){
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "验证码有误", Toast.LENGTH_SHORT).show();
                }



                break;
        }
    }


    //将收到的验证码和手机号提交再次核对

    /**
     * 判断手机号码是否合理
     *
     * @paramphoneNums
     */

    private boolean judgePhoneNum(String phoneNum) {
        if (isMatchLength(phoneNum, 11) && isMobileNO(phoneNum)) {
            return true;
        } else {
            Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 判断一个字符串的位数
     *
     * @return
     * @paramstr
     * @paramlength
     */

    public static boolean isMatchLength(String str, int length) {

        if (str.isEmpty()) {

            return false;

        } else {

            return str.length() == length ? true : false;

        }

    }

    /**
     * 验证手机格式
     */

    public static boolean isMobileNO(String mobileNums) {

/*

* 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188

* 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）

* 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9

*/


        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(mobileNums))

            return false;

        else

            return mobileNums.matches(telRegex);

    }

    Handler handler = new Handler() {

        @Override

        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                mGetCodeBtn.setText("重新发送" + i + "s");
            } else if (msg.what == 2) {
                mGetCodeBtn.setText("获取验证码");
                mGetCodeBtn.setClickable(true);
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(RegisterActivity.this,
//
//                                OkActivity.class);
//
//                        startActivity(intent);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        Toast.makeText(getApplicationContext(), "正在获取验证码",
                                Toast.LENGTH_SHORT).show();
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                }
            }
        }
    };

    @Override

    protected void onDestroy() {

        SMSSDK.unregisterAllEventHandler();

        super.onDestroy();

    }

}
