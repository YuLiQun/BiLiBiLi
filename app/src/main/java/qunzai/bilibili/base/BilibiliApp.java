package qunzai.bilibili.base;

import android.app.Application;
import android.content.Context;
import android.view.Window;

import cn.bmob.v3.Bmob;
import cn.smssdk.SMSSDK;

/**
 * Created by dllo on 16/11/28.
 */

public class BilibiliApp extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {

        super.onCreate();
        sContext = this;
        SMSSDK.initSDK(this, "195c78c403924", "d61cb5d7e23d375ce9ae52fafb6cf466");
        Bmob.initialize(this, "00a35f00d3c791da2d3bd59f744d361d");


    }



    public static Context getContext() {
        return sContext;
    }
}
