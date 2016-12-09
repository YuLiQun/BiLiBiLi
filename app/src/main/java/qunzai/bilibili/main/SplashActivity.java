package qunzai.bilibili.main;


import android.content.Intent;
import android.widget.RelativeLayout;

import java.util.concurrent.TimeUnit;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cankered2008 on 2016/12/8.
 */

public class SplashActivity extends BaseActivity{

    private RelativeLayout mRelativeLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_splash);

    }

    @Override
    protected void initData() {
        setUpSplash();
    }

    private void setUpSplash() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong) {

                        return Observable.just("Login");
                    }
                }).subscribe(s -> {
            if(s.equals("Login")){
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
