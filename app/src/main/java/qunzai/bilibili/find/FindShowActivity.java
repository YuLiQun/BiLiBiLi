package qunzai.bilibili.find;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.bean.FindSearchBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.internet.UrlClass;

/**
 * Created by QunZai on 16/12/8.
 */

public class FindShowActivity  extends BaseActivity implements TextWatcher {

    private EditText mSeachEt;
    private ListView mSeachLv;
    private FindSearchAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_find_show;
    }

    @Override
    protected void initViews() {
        mSeachEt = bindView( R.id.fragment_find_search_et);
        mSeachLv = bindView( R.id.fragment_find_search_lv);
        mSeachEt.addTextChangedListener(this);

    }

    @Override
    protected void initData() {




    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        if (charSequence.length() > 0) {
            //显示listView
            mSeachLv.setVisibility(View.VISIBLE);
            //网络请求
            initInternet();
        } else {
            mSeachLv.setVisibility(View.INVISIBLE);
            // 隐藏listView
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    private void initInternet() {
        String content = mSeachEt.getText().toString();
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(UrlClass.URL_SEARCH(content), FindSearchBean.class, new ResponseCallBack<FindSearchBean>() {



            @Override
            public void onResponse(FindSearchBean findSearchBean) {
//                Log.d("fff", findSearchBean.getTag().getValue1().getName());
                mAdapter = new FindSearchAdapter(findSearchBean);
                mSeachLv.setAdapter(mAdapter);

                initLVClick();
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }

    private void initLVClick() {
        mSeachLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FindShowActivity.this,FindContentActivity.class);
                intent.putExtra("search",mSeachEt.getText().toString());
                startActivity(intent);
            }
        });
    }
}
