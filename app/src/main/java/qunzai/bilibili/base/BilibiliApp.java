package qunzai.bilibili.base;

import android.app.Application;
import android.content.Context;

import android.view.Window;

import cn.bmob.v3.Bmob;
import cn.smssdk.SMSSDK;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import static qunzai.bilibili.utils.DensityUtils.px2dip;


/**
 * Created by dllo on 16/11/28.
 */

public class BilibiliApp extends Application{
    private static Context sContext;
    private static int screenWidth;
    private static int screenHeight;

    @Override
    public void onCreate() {

        super.onCreate();
        sContext = this;

        SMSSDK.initSDK(this, "195c78c403924", "d61cb5d7e23d375ce9ae52fafb6cf466");
        Bmob.initialize(this, "00a35f00d3c791da2d3bd59f744d361d");



        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        screenWidth = px2dip(this,displayMetrics.widthPixels);
        screenHeight = px2dip(this,displayMetrics.heightPixels);

    }



    public static Context getContext() {
        return sContext;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}
