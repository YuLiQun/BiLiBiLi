package qunzai.bilibili.player;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.bean.CategoriesBean;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

/**
 * Created by XingMingDa on 16/11/24.
 * IjkPlayer+Danmaku
 */

public class IjkPlayerActivity extends BaseActivity {

    private IjkVideoView mIjkVideoView;
    private String mVideoPath = "";
    private boolean mBackPressed;
    private DanmakuView mDanmakuView;
    private boolean mShowDanmaku;
    private DanmakuContext mDanmakuContext;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private AndroidMediaController mMediaController;
    private ActionBar mActionBar;
    private LinearLayout mOperationLayout;
    private ImageView mSend;
    private EditText mEditText;
    private Bundle mBundle;

    @Override
    protected int getLayout() {
        return R.layout.activity_ijk_player_test;
    }

    @Override
    protected void initViews() {
        mIjkVideoView = bindView(R.id.activity_ijk_player_ijk_video_view);
        mDanmakuView = bindView(R.id.activity_ijk_player_danmaku_view);
        mActionBar = getSupportActionBar();
        if(mActionBar != null){
            mActionBar.hide();
        }
        mOperationLayout = (LinearLayout) findViewById(R.id.activity_ijk_player_operation_layout);
        mSend = bindView(R.id.activity_ijk_player_send);
        mEditText = bindView(R.id.activity_ijk_player_edit_text);
    }

    @Override
    protected void initData() {
        mBundle = getIntent().getBundleExtra("Categories2IjkPlayerData");
        if(mBundle != null){
            CategoriesBean bean = (CategoriesBean) mBundle.get("Data");
            int position = mBundle.getInt("Position");
            mVideoPath = bean.getData().get(position).getPlayurl();
        }
        mBundle = getIntent().getBundleExtra("Live2IjkPlayerData");
        if(mBundle != null){
            mVideoPath = mBundle.getString("Data");
        }
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mMediaController = new AndroidMediaController(this,false);
        mIjkVideoView.setMediaController(mMediaController);
        if (mIjkVideoView != null) {
            mIjkVideoView.setVideoPath(mVideoPath);
        }
        mIjkVideoView.start();
        mDanmakuView.enableDanmakuDrawingCache(true);
        mDanmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                mShowDanmaku = true;
                mDanmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        mDanmakuContext = DanmakuContext.create();
        mDanmakuView.prepare(parser, mDanmakuContext);

        mDanmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOperationLayout.getVisibility() == View.GONE){
                    mOperationLayout.setVisibility(View.VISIBLE);
                }else {
                    mOperationLayout.setVisibility(View.GONE);
                }
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEditText.getText().toString();
                if(!TextUtils.isEmpty(content)){
                    addDanmaku(content,true);
                    mEditText.setText("");
                }
            }
        });
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                if(i == View.SYSTEM_UI_FLAG_VISIBLE){
                    onWindowFocusChanged(true);
                }
            }
        });
    }

    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mShowDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(mDanmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        mDanmakuView.addDanmaku(danmaku);
    }

    private int sp2px(int spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()){
            mDanmakuView.resume();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mBackPressed = true;
    }

    @Override
    protected void onStop() {
        super.onStop();

        mShowDanmaku = false;
        if(mDanmakuView != null){
            mDanmakuView.release();
            mDanmakuView = null;
        }

        if (mBackPressed || mIjkVideoView.isBackgroundPlayEnabled()) {
            mIjkVideoView.stopPlayback();
            mIjkVideoView.release(true);
            mIjkVideoView.stopBackgroundPlay();
        } else {
            mIjkVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
