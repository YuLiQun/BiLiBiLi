package qunzai.bilibili.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import qunzai.bilibili.R;

/**
 * Created by dllo on 16/11/21.
 */

public abstract class BaseActivity  extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //绑定布局
        setContentView(getLayout());
        initViews();
        initData();
    }

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract int getLayout();

    protected <T extends View>T bindView(int id){
        return (T) findViewById(id);
    }

    protected <T extends View>T bindView(View view,int id){
        return (T) view.findViewById(id);
    }

    protected void setClick(View.OnClickListener clickListener,View...views){
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }

}
