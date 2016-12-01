package qunzai.bilibili.communication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;
import com.turing.androidsdk.asr.VoiceRecognizeListener;
import com.turing.androidsdk.asr.VoiceRecognizeManager;
import com.turing.androidsdk.tts.TTSListener;
import com.turing.androidsdk.tts.TTSManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

public class TuringActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = TuringActivity.class.getSimpleName();
    private TTSManager mTTSManager;
    private VoiceRecognizeManager mRecognizeManager;
    private TuringApiManager mTuringApiManager;
    /**
     * 返回结果，开始说话
     */
    public final int SPEECH_START = 0;
    public final int SPEECH_START_TWO = 4;
    /**
     * 开始识别
     */
    public final int RECOGNIZE_RESULT = 1;
    /**
     * 开始识别
     */
    public final int RECOGNIZE_START = 2;
    public final int WRITE_START = 3;

    /**
     * 申请的turing的apikey
     **/
    private final String TURING_APIKEY = "70af43f4fecc45109483d691c6df2249";
    private final String TURING_APIKEY_TWO = "a15da4fa3f2840d09accef68d3f69b4c";

    /**
     * 申请的secret
     **/
    private final String TURING_SECRET = "b207a7f7a669a2dc";
    private final String TURING_SECRET_TWO = "0f1ef8fecf567ded";
    /**
     * 填写一个任意的标示，没有具体要求，，但一定要写，
     **/
    private final String UNIQUEID = "8933680";
    //百度key
    private final String BD_APIKEY = "kzlNupi8iTUZldbNmCk7c01t";
    //百度screte
    private final String BD_SECRET = "91c1c731195143a93d5f255a6329fcbc";

    private EditText mDialogueEt;
    private Button mLongBtn;
    private Button mVoiceBtn;
    private Button mSendBtn;
    private ArrayList<TuringBean> mArrayList;
    private TuringAdapter mAdapter;
    private RecyclerView mTuringRv;
    private TuringBean mTuringBean;
    private TextView mKeyboardTv;


    @Override
    protected int getLayout() {
        return R.layout.activity_turing;
    }

    @Override
    protected void initViews() {
        mTuringRv = bindView(R.id.activity_turing_comm_rv);
        mDialogueEt = bindView(R.id.activity_turing_dialogue_et);
        mLongBtn = bindView(R.id.activity_turing_long_send_btn);
        mVoiceBtn = bindView(R.id.activity_turing_voice_btn);
        mSendBtn = bindView(R.id.activity_turing_send_btn);
        mKeyboardTv = bindView(R.id.activity_turing_keyboard_tv);
        mDialogueEt.addTextChangedListener(watcher);
        setClick(this, mVoiceBtn, mSendBtn, mLongBtn, mDialogueEt, mKeyboardTv);

        mTuringRv.setLayoutManager(new LinearLayoutManager(this));
        TuringAdapter adapter = new TuringAdapter(this);
        mTuringRv.setAdapter(adapter);
//        Intent intent = getIntent();

    }

    @Override
    protected void initData() {
        mArrayList = new ArrayList<>();
        mRecognizeManager = new VoiceRecognizeManager(this, BD_APIKEY, BD_SECRET);
        mTTSManager = new TTSManager(this, BD_APIKEY, BD_SECRET);
        mRecognizeManager.setVoiceRecognizeListener(myVoiceRecognizeListener);
        mTTSManager.setTTSListener(myTTSListener);
        mAdapter = new TuringAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mTuringRv.setLayoutManager(manager);
        mTuringRv.setAdapter(mAdapter);

        SDKInitBuilder builder = new SDKInitBuilder(this).
                setTuringKey(TURING_APIKEY).setSecret(TURING_SECRET).setUniqueId(UNIQUEID);
        SDKInit.init(builder, new InitListener() {
            @Override
            public void onComplete() {
                mTuringApiManager = new TuringApiManager(TuringActivity.this);
                mTuringApiManager.setHttpListener(myHttpConnectionListener);
                mTTSManager.startTTS("你好");


            }

            @Override
            public void onFail(String s) {
                Log.d("TuringActivity", TAG + s);

            }
        });
    }

    private Handler myHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SPEECH_START:

                    mTuringBean = new TuringBean();
                    mTuringBean.setType(0);
                    mTuringBean.setDialogue("" + (String) msg.obj);
                    mArrayList.add(mTuringBean);
                    mAdapter.setArrayList(mArrayList);
                    mAdapter.notifyDataSetChanged();
                    mTTSManager.startTTS((String) msg.obj);
                    break;
                case RECOGNIZE_RESULT:
                    break;
                case RECOGNIZE_START:

                    break;
                default:
                    break;
            }
        }
    };


    HttpConnectionListener myHttpConnectionListener = new HttpConnectionListener() {
        @Override
        public void onSuccess(RequestResult result) {
            if (result != null) {
                try {
                    Log.d(TAG, result.getContent().toString());
                    JSONObject result_obj = new JSONObject(result.getContent()
                            .toString());
                    if (result_obj.has("text")) {
                        Log.d(TAG, result_obj.get("text").toString());
                        myHandler.obtainMessage(SPEECH_START,
                                result_obj.get("text")).sendToTarget();
                        //// TODO: 16/11/25
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "JSONException:" + e.getMessage());
                }
            }
        }

        @Override
        public void onError(ErrorMessage errorMessage) {
            Log.d(TAG, errorMessage.getMessage());
        }

    };

    /**
     * 语音识别回调
     */
    VoiceRecognizeListener myVoiceRecognizeListener = new VoiceRecognizeListener() {

        @Override
        public void onVolumeChange(int volume) {
            // 仅讯飞回调
        }

        @Override
        public void onStartRecognize() {
            // 仅针对百度回调


        }

        @Override
        public void onRecordStart() {

        }

        @Override
        public void onRecordEnd() {

        }

        @Override
        public void onRecognizeResult(String result) {
            Log.d(TAG, "识别结果：" + result);
            if (result == null) {
                mRecognizeManager.startRecognize();
                myHandler.sendEmptyMessage(RECOGNIZE_START);
                return;
            }

            mTuringApiManager.requestTuringAPI(result);
            myHandler.obtainMessage(RECOGNIZE_RESULT, result).sendToTarget();

            mTuringBean = new TuringBean();
            mTuringBean.setDialogue(result);
            mTuringBean.setType(1);
            mArrayList.add(mTuringBean);
            mAdapter.setArrayList(mArrayList);


        }

        @Override
        public void onRecognizeError(String error) {
            Log.e(TAG, "识别错误：" + error);
            mRecognizeManager.startRecognize();
            myHandler.sendEmptyMessage(RECOGNIZE_START);


        }
    };
    /**
     * TTS回调
     */
    TTSListener myTTSListener = new TTSListener() {

        @Override
        public void onSpeechStart() {
            Log.d(TAG, "TTS Start!");
        }

        @Override
        public void onSpeechProgressChanged() {

        }

        @Override
        public void onSpeechPause() {
            Log.d(TAG, "TTS Pause!");
        }

        @Override
        public void onSpeechFinish() {
            Log.d("TuringActivity", "读完了");
            myHandler.obtainMessage(RECOGNIZE_START).sendToTarget();


        }

        @Override
        public void onSpeechError(int errorCode) {
            Log.d(TAG, "TTS错误，错误码：" + errorCode);
        }


        @Override
        public void onSpeechCancel() {
            Log.d(TAG, "TTS Cancel!");
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_turing_voice_btn:
                mVoiceBtn.setVisibility(View.GONE);
                mDialogueEt.setVisibility(View.GONE);
                mLongBtn.setVisibility(View.VISIBLE);
                mSendBtn.setVisibility(View.GONE);
                mKeyboardTv.setVisibility(View.VISIBLE);


                break;
            case R.id.activity_turing_send_btn:
                String dialogue = mDialogueEt.getText().toString();
                mTuringApiManager.requestTuringAPI(dialogue);
                myHandler.obtainMessage(RECOGNIZE_RESULT, dialogue).sendToTarget();
                mTuringBean = new TuringBean();
                mTuringBean.setDialogue(mDialogueEt.getText().toString());
                mTuringBean.setType(1);
                mArrayList.add(mTuringBean);
                mAdapter.setArrayList(mArrayList);
                mAdapter.notifyDataSetChanged();
                mDialogueEt.setText("");
                break;
            case R.id.activity_turing_long_send_btn:
                mRecognizeManager.startRecognize();
                Log.d("TuringActivity", "start");


                break;
            case R.id.activity_turing_keyboard_tv:
                mVoiceBtn.setVisibility(View.VISIBLE);
                mDialogueEt.setVisibility(View.VISIBLE);
                mLongBtn.setVisibility(View.GONE);
                mKeyboardTv.setVisibility(View.GONE);
                break;

        }
    }


    private TextWatcher watcher = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (mDialogueEt.getText().toString().length() > 0) {

                mSendBtn.setVisibility(View.VISIBLE);
            } else {
                mSendBtn.setVisibility(View.GONE);
            }


        }
    };
}
