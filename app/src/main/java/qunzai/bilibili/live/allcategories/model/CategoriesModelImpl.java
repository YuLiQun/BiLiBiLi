package qunzai.bilibili.live.allcategories.model;

import qunzai.bilibili.bean.CategoriesBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.live.allcategories.presenter.OnGetCategoriesBeanListener;

/**
 * Created by XingMingDa on 16/12/6.
 */

public class CategoriesModelImpl implements CategoriesModel {
    @Override
    public void getCategoriesRequest(String url, Class clazz, final OnGetCategoriesBeanListener listener) {
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(url, clazz, new ResponseCallBack<CategoriesBean>() {
            @Override
            public void onResponse(CategoriesBean categoriesBean) {
                listener.onSuccess(categoriesBean);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }
}
