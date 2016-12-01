package qunzai.bilibili.drawer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseFragment;
import qunzai.bilibili.drawer.login.LoginActivity;

/**
 * Created by QunZai on 16/11/24.
 */

public class DrawerFragment extends BaseFragment {

    private ListView mLv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_drawer;
    }

    @Override
    protected void initViews() {
        mLv = bindView(R.id.fragment_drawer_lv);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.item_drawer_header,null);
        mLv.addHeaderView(headView);
    }

    @Override
    protected void initData() {
        DrawerAdapter adapter = new DrawerAdapter();
        mLv.setAdapter(adapter);
        mLvClick();
    }

    private void mLvClick() {
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(mContext,LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


}
