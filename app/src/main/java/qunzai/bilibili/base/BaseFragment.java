package qunzai.bilibili.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qunzai.bilibili.R;

/**
 * Created by dllo on 16/11/21.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayout() == 0) {
            return inflater.inflate(R.layout.null_layout, container, false);
        } else {
            return inflater.inflate(getLayout(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void initData();


    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }


    protected void setClick(View.OnClickListener clickListener, View... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }
}
