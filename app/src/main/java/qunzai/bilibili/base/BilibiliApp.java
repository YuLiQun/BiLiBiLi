package qunzai.bilibili.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/11/28.
 */

public class BilibiliApp extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }
}
