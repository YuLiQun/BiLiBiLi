package qunzai.bilibili.main;

import android.graphics.Color;
import android.support.v7.app.ActionBar;

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
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

/**
 * Created by XingMingDa on 16/11/24.
 * IjkPlayer+Danmaku
 */

public class IjkPlayerTestActivity extends BaseActivity {

    private IjkVideoView mIjkVideoView;
    private String mVideoPath = "http://xl.live-play.acgvideo.com/live-xl/570653/live_185546_1530569.flv?wsSecret=6cca1e4d59cc9fb80cb3124573de1b52&wsTime=1479982801";
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

    @Override
    protected int getLayout() {
        return R.layout.activity_ijk_player_test;
    }

    @Override
    protected void initViews() {
        mIjkVideoView = (IjkVideoView) findViewById(R.id.activity_ijk_player_test_ijk_video_view);
        mDanmakuView = (DanmakuView) findViewById(R.id.activity_ijk_player_test_danmaku_view);
        mActionBar = getSupportActionBar();
        if(mActionBar != null){
            mActionBar.hide();
        }
    }

    @Override
    protected void initData() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mMediaController = new AndroidMediaController(this,false);
//        mMediaController.setSupportActionBar(mActionBar);
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
